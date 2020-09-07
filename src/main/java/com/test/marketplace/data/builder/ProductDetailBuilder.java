package com.test.marketplace.data.builder;


import com.test.marketplace.common.data.builder.CommonBuilder;
import com.test.marketplace.common.data.util.Conditional;
import com.test.marketplace.common.data.util.FilterUtil;

public class ProductDetailBuilder extends CommonBuilder {

	private static final long serialVersionUID = 3675676056604759098L;
	
	@FilterUtil(field = "id",condition = Conditional.EQUAL, join = "product")
	private Long idProduct;

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}
}
