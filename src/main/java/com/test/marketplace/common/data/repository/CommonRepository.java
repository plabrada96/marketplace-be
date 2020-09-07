package com.test.marketplace.common.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<T,K> extends JpaRepository<T, K>, JpaSpecificationExecutor<T>{

}
