package com.accenture.enterprise.controller.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.RepresentationModel;

import com.accenture.enterprise.controller.ProductController;
import com.accenture.enterprise.entities.Product;

public class ProductModel extends RepresentationModel<ProductModel> {

	private final Product product;

	public ProductModel(Product product) {
		super();
		this.product = product;
		add(linkTo(ProductController.class).withRel("products"));
		add(linkTo(ProductController.class).slash(product.getId()).withSelfRel());
	}

	public Product getProduct() {
		return product;
	}

	
	
}
