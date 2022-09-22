package com.accenture.enterprise.repository;

import org.springframework.data.repository.CrudRepository;

import com.accenture.enterprise.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
