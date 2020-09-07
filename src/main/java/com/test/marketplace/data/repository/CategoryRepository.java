package com.test.marketplace.data.repository;

import org.springframework.stereotype.Repository;

import com.test.marketplace.common.data.repository.CommonRepository;
import com.test.marketplace.data.Category;

@Repository
public interface CategoryRepository extends CommonRepository<Category, Long>{

}
