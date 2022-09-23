package com.accenture.enterprise.repository;

import org.springframework.data.repository.CrudRepository;

import com.accenture.enterprise.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
