package com.accenture.enterprise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

	public List<Customer> findAll() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().iterator().forEachRemaining(customers::add);

		return customers;
	}

	public void create(Customer customer) {
		customerRepository.save(customer);

	}

	public Customer findById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			return customer.get();
		}

		throw new NoSuchElementException("No Customer with id: " + id);
	}

	public void update(Customer customer) {
		Optional<Customer> customerOptional = customerRepository.findById(customer.getId());

		if (customerOptional.isPresent()) {
			customerRepository.save(customer);

		} else {
			throw new IllegalArgumentException("No Customer to update with id: " + customer.getId());
		}

	}

	public void delete(Long id) {
		try {
			customerRepository.deleteById(id);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();

		}
	}

}
