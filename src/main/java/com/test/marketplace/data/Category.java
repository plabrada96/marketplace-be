package com.test.marketplace.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.test.marketplace.common.data.GenericEntity;
import com.test.marketplace.common.vo.StatusType;
import com.test.marketplace.constants.UtilConstants;

@Entity
@Table(schema=UtilConstants.SCHEMA_DB, name ="category")
public class Category extends GenericEntity{

	private static final long serialVersionUID = -8892548281055489302L;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "status")
	private Character status;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	public Category() {
		super();
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
