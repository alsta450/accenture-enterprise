package com.accenture.enterprise.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.accenture.enterprise.controller.hateoas.CustomerModel;
import com.accenture.enterprise.entities.Customer;
import com.accenture.enterprise.service.CustomerService;

@RestController
@RequestMapping(value = "/customers", produces = "application/hal+json")
public class CustomerController {

	public CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}


	@GetMapping
	public ResponseEntity<CollectionModel<CustomerModel>> findAll() {
		return ResponseEntity
				.ok(CollectionModel.of(customerService.findAll()).add(linkTo(CustomerController.class).withSelfRel()));
	}

	@PostMapping
	public ResponseEntity<CustomerModel> create(@RequestBody Customer customer) {
		final URI uri = MvcUriComponentsBuilder.fromController(getClass()).build().toUri();
		return ResponseEntity.created(uri).body(customerService.create(customer));

	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerModel> findById(@PathVariable Long id) {

		return ResponseEntity.ok(customerService.findById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		customerService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping
	public ResponseEntity<CustomerModel> update(@RequestBody Customer customer) {

		CustomerModel customerModel = customerService.update(customer);
		final URI uri = MvcUriComponentsBuilder.fromController(getClass()).build().toUri();
		return ResponseEntity.created(uri).body(customerModel);
	}
}
