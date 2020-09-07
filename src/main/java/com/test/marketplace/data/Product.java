package com.test.marketplace.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.test.marketplace.common.data.GenericEntity;
import com.test.marketplace.common.vo.StatusType;
import com.test.marketplace.constants.UtilConstants;

@Entity
@Table(schema=UtilConstants.SCHEMA_DB, name ="product")
public class Product extends GenericEntity {

	private static final long serialVersionUID = 7516219351758013530L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_category")
	private Category category;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "status")
	private Character status;
	
	@Lob
	private String image;
	
	public Product() {
		super();
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category idCategory) {
		this.category = idCategory;
	}


	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public StatusType getStatus() {
		return StatusType.searchValue(status);
	}
	public void setStatus(StatusType status) {
		this.status = status.getValue();
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
