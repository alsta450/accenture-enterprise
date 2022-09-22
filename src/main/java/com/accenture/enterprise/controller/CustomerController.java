package com.accenture.enterprise.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.enterprise.entities.Customer;
import com.accenture.enterprise.repository.CustomerRepository;
import com.accenture.enterprise.service.CustomerService;

@RestController
public class CustomerController {

	
	private CustomerService customerService;
	
	
	
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}



	@GetMapping("/customers")
	public List<Customer> list(){
		
		return customerService.list();

	}
	
}
