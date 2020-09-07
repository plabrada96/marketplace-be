package com.test.marketplace.common.data;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class GenericEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5487187229272906255L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
