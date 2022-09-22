package com.accenture.enterprise.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.enterprise.entities.Customer;
import com.accenture.enterprise.service.CustomerService;

@RestController
public class CustomerController {

	
	public CustomerService customerService;
	
	
	
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}


	@GetMapping("/customers")
	public List<Customer> findAll(){
		return customerService.findAll();
	}
	
	@PostMapping("/customers")
	public void create(@RequestBody Customer customer){
		
		customerService.create(customer);
	}
	
	@GetMapping("/customers/{id}")
	public Customer findById(@PathVariable Long id){
		
		return customerService.findById(id);
	}
	
	@DeleteMapping("/customers/{id}")
	public void delete(@PathVariable Long id){
		
		customerService.delete(id);
	}
	
	@PutMapping("/customers")
	public void update(@RequestBody Customer customer){
		
		customerService.update(customer);
	}
}
