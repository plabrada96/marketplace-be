package com.test.marketplace.common.webcontroller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.test.marketplace.constants.MappingConstants;

@PropertySource("classpath:lang/lang-${marketplace.lang}.properties")
public abstract class CommonController<T> {

	@GetMapping(MappingConstants.CONSULT_STANDARD) 
	public abstract T consult(@PathVariable(MappingConstants.PATH_ID) Long id);

	@PostMapping(MappingConstants.SAVE_STANDARD)
	public abstract T save(@RequestBody(required = true) T object);

	@PutMapping(MappingConstants.UPDATE_STANDARD)
	public abstract T update(@PathVariable(MappingConstants.PATH_ID) Long id, @RequestBody(required = true) T object);

	@DeleteMapping(MappingConstants.DELETE_STANDARD)
	public abstract void delete(@PathVariable(MappingConstants.PATH_ID) Long id);
	
}
