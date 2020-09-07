package com.test.marketplace.common.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.marketplace.common.data.repository.CommonRepository;
import com.test.marketplace.common.data.specifiers.CommonSpecifications;


@Service
public abstract class CommonService<T, K> {

	private CommonRepository<T, K> repository;

	public CommonRepository<T, K> getRepository() {
		return repository;
	}

	@Autowired
	public void setRepository(CommonRepository<T, K> repository) {
		this.repository = repository;
	}

	public T save(T o) {
		return repository.save(o);
	}
	
	public void delete(K id) {
		repository.deleteById(id);
	}

	public T consult(K id) {
		try {
			return repository.findById(id).get();
		} catch (NoSuchElementException ex) {
			return null;
		}
	}


	public List<T> list() {
		return repository.findAll();
	}
	
	public Page<T> list(Integer page, Integer size) {
		return repository.findAll(PageRequest.of(page, size));
	}
	
	public Page<T> list(CommonSpecifications<T> searchParameteres){
		if (searchParameteres.getBuilder().getSize() != null && searchParameteres.getBuilder().getPage() != null) {
			return repository.findAll(searchParameteres.getSpecification(), PageRequest.of(searchParameteres.getBuilder().getPage(), searchParameteres.getBuilder().getSize()));
		}
		return repository.findAll(searchParameteres.getSpecification(),Pageable.unpaged());
	}
	
	public Long count() {
		return repository.count();
	}
	
}
