package com.accenture.enterprise.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import com.accenture.enterprise.controller.CustomerController;
import com.accenture.enterprise.entities.Customer;

public class CustomerModel extends RepresentationModel<CustomerModel> {

	private final Customer customer;

	public CustomerModel(final Customer customer) {
		this.customer = customer;
		add(linkTo(CustomerController.class).withRel("customers"));
		add(linkTo(CustomerController.class).slash(customer.getId()).withSelfRel());
	}

	public Customer getCustomer() {
		return customer;
	}

}
