package com.test.marketplace.data.builder;

import com.test.marketplace.common.data.builder.CommonBuilder;
import com.test.marketplace.common.data.util.Conditional;
import com.test.marketplace.common.data.util.FilterUtil;

public class ProductBuilder extends CommonBuilder {
	
	private static final long serialVersionUID = -7361811517498634112L;
	
	@FilterUtil(field = "id",condition = Conditional.EQUAL, join = "category")
	private Long categoryId;
	
	@FilterUtil(field = "name",condition = Conditional.LIKE)
	private String name;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
