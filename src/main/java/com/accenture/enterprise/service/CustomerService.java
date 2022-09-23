package com.accenture.enterprise.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.accenture.enterprise.controller.CustomerController;
import com.accenture.enterprise.entities.Customer;
import com.accenture.enterprise.model.CustomerModel;
import com.accenture.enterprise.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}

	public List<CustomerModel> findAll() {

		List<CustomerModel> customerModels = new ArrayList<>();
		customerRepository.findAll().iterator()
				.forEachRemaining(customer -> customerModels.add(new CustomerModel(customer)));
		;
		return customerModels;
	}

	public CustomerModel create(Customer customer) {
		customerRepository.save(customer);
		return new CustomerModel(customer);
	}

	public CustomerModel findById(Long id) {
		return customerRepository.findById(id).map(customer -> new CustomerModel(customer))
				.orElseThrow(() -> new NoSuchElementException("No Customer with id: " + id));
	}

	public CustomerModel update(Customer customer) {
		Optional<Customer> customerOptional = customerRepository.findById(customer.getId());

		if (customerOptional.isPresent()) {
			customerRepository.save(customer);
			return new CustomerModel(customer);
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
