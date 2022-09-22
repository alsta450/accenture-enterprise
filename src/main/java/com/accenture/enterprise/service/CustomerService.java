package com.accenture.enterprise.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accenture.enterprise.entities.Customer;
import com.accenture.enterprise.repository.CustomerRepository;


@Service
public class CustomerService {


	private CustomerRepository customerRepository;
	
	
	

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}




	public List<Customer> list(){
		
		List<Customer> customers = new ArrayList<>();
		
		customerRepository.findAll().iterator().forEachRemaining(customers::add);
	
		return customers;
	}

}
