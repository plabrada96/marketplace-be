package com.test.marketplace.data.repository;

import org.springframework.stereotype.Repository;

import com.test.marketplace.common.data.repository.CommonRepository;
import com.test.marketplace.data.Product;

@Repository
public interface ProductRepository extends CommonRepository<Product, Long>{

}
