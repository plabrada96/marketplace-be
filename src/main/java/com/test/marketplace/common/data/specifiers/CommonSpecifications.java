/**
 * 
 */
package com.test.marketplace.common.data.specifiers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.test.marketplace.common.data.builder.CommonBuilder;
import com.test.marketplace.common.data.util.FilterUtil;
import com.test.marketplace.common.vo.IType;
import com.test.marketplace.constants.UtilConstants;


@Component
public class CommonSpecifications<Entity>{
	
	private static final Logger LOGGER = Logger.getLogger(CommonSpecifications.class.getName());
	private CommonBuilder builder;
	
	public CommonSpecifications() {
		
	}
	
	public CommonSpecifications(CommonBuilder builder) {
		this.builder = builder;
	}

	public CommonBuilder getBuilder() {
		return builder;
	}

	public void setBuilder(CommonBuilder builder) {
		this.builder = builder;
	}
	
	private String containsLowerCase(String searchField) {
		return UtilConstants.WILDCARD + searchField.toLowerCase() + UtilConstants.WILDCARD;
	}
	
	public Specification<Entity> getSpecification(){
		List<Specification<Entity>> listSpecifications = new ArrayList<>();
		try {
			Field[] fields= builder.getClass().getDeclaredFields();
			for (Field field : fields) {
				FilterUtil fieldFiltro = field.getAnnotation(FilterUtil.class);
				if(fieldFiltro!=null) {
					Method method = builder.getClass().getMethod(getterMethodName(field.getName()), new Class[] {});
					Object valor = method.invoke(builder, new Object[] {});
					if(valor!=null) {
						switch (fieldFiltro.condition()) {
						case EQUAL:
							listSpecifications.add(this.filterEqual(fieldFiltro, valor));
							break;
						case LIKE:
							listSpecifications.add(this.filterLike(fieldFiltro, valor.toString()));
							break;
						case IN:
							listSpecifications.add(this.filterIn(fieldFiltro, valor));
							break;
						case SPECIFIED:
							listSpecifications.add(this.filterIsNull(fieldFiltro, (boolean)valor));
							break;
						default:
							listSpecifications.add(this.filterEqual(fieldFiltro, valor));
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.getMessage(), e);
		}
		return this.buildAnd(listSpecifications);
	}
	
	public Specification<Entity> filterEqual(FilterUtil fieldFiltro,Object value){
		return (root, query, criteriaBuilder) -> {
			
			Object valor = null;
			if(value instanceof IType)  {
				valor = ((IType)value).getValue();
			} else {
				valor = value;
			}
			
			if(fieldFiltro.join().isEmpty()) {
				return criteriaBuilder.equal(root.get(fieldFiltro.field()), valor);
			}else {
				return criteriaBuilder.equal(getJoin(root, fieldFiltro.join()).get(fieldFiltro.field()), valor);
			}
		};
	}
	
	public Specification<Entity> filterIsNull(FilterUtil fieldFiltro,boolean value){
		return (root, query, criteriaBuilder) -> {
			if(fieldFiltro.join().isEmpty()) {
				return value ? criteriaBuilder.isNotNull(root.get(fieldFiltro.field())) : criteriaBuilder.isNull(root.get(fieldFiltro.field()));
			}else {
				return value ? criteriaBuilder.isNotNull(getJoin(root, fieldFiltro.join()).get(fieldFiltro.field())) : criteriaBuilder.isNull(getJoin(root, fieldFiltro.join()).get(fieldFiltro.field()));
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public Specification<Entity> filterLike(FilterUtil fieldFiltro,Object value){
		return (root, query, criteriaBuilder) -> {
			if(fieldFiltro.join().isEmpty()) {
				if(fieldFiltro.concat().isEmpty()) {
					return criteriaBuilder.like(criteriaBuilder.lower(root.get(fieldFiltro.field())), containsLowerCase(value.toString()));
				}else {
					Expression<String> exp1 = criteriaBuilder.concat(criteriaBuilder.lower(root.get(fieldFiltro.field())), " ");
					Expression<String> exp2 = criteriaBuilder.concat(exp1,criteriaBuilder.lower(root.get(fieldFiltro.concat())));
					return criteriaBuilder.like(exp2, containsLowerCase(value.toString()));
				}
				
			}else {
				return criteriaBuilder.like(criteriaBuilder.lower(getJoin(root, fieldFiltro.join()).get(fieldFiltro.field())), containsLowerCase(value.toString()));
			}
		};
	}
	
	@SuppressWarnings("unchecked")
	public Specification<Entity> filterIn(FilterUtil fieldFiltro,Object values){
		return (root, query, criteriaBuilder) -> {
			Collection<Object> listaValores = null;
			if(values instanceof IType[]) {
				List<IType> listaItypes = Arrays.asList((IType[])values);
				listaValores = new LinkedList<>();
				for (IType Itype : listaItypes) {
					listaValores.add(Itype.getValue());
				}
			}else {
				if(values instanceof Object[]) {
					Object[] valuesArray = (Object[]) values;
					listaValores = Arrays.asList(valuesArray);
				}else {
					listaValores = Arrays.asList(values);
				}	
			}
			
			if(fieldFiltro.join().isEmpty()) {
				return root.get(fieldFiltro.field()).in(listaValores);
			}else {
				return getJoin(root, fieldFiltro.join()).get(fieldFiltro.field()).in(listaValores);
			}
		};
	}
	
	public Specification<Entity> buildAnd(List<Specification<Entity>> listSpecifications){
		Specification<Entity> result = null;
		if(listSpecifications != null && !listSpecifications.isEmpty()) {
			result = listSpecifications.get(0);
			for (int i = 1; i < listSpecifications.size(); i++) {
		        result = Specification.where(result).and(listSpecifications.get(i)); //warning 1
		    }
		}
		return result;
	}
	
	public static String getterMethodName(String propertyName) {
        if (null == propertyName || 0 == propertyName.length()) {
            return propertyName;
        }
        StringBuffer sbuff = new StringBuffer(propertyName);
        char firstLetter = sbuff.charAt(0);
        firstLetter = Character.toUpperCase(firstLetter);
        sbuff.setCharAt(0, firstLetter);
        sbuff.insert(0, "get");
        return sbuff.toString();
    }
	
	@SuppressWarnings({ "rawtypes" })
	protected Join getJoin(Root root,String join){
		Join joinEntity = null;
		String[] arraysJoins = join.split("\\.");
		for (String strJoin : arraysJoins) {
			if(joinEntity==null) {
				joinEntity = root.join(strJoin);
			}else {
				joinEntity = joinEntity.join(strJoin);
			}
		}
		return joinEntity;
	}
}
