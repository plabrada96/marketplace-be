package com.test.marketplace.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.test.marketplace.common.data.GenericEntity;
import com.test.marketplace.constants.UtilConstants;

@Entity
@Table(schema=UtilConstants.SCHEMA_DB, name ="product_detail")
public class ProductDetail extends GenericEntity {

	private static final long serialVersionUID = -7321372148343663458L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_product")
	private Product product;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "brand")
	private String brand;
	
	@Column(name = "material")
	private String material;
	
	@Column(name = "stock")
	private Integer stock;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	public ProductDetail() {
		super();
	}
	
	
	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}





	public String getMaterial() {
		return material;
	}


	public void setMaterial(String material) {
		this.material = material;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
