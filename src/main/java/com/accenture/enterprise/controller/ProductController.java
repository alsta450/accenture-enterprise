package com.accenture.enterprise.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
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

import com.accenture.enterprise.controller.hateoas.ProductModel;
import com.accenture.enterprise.entities.Product;
import com.accenture.enterprise.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<CollectionModel<ProductModel>> findAll() {		
		return ResponseEntity
				.ok(CollectionModel.of(productService.findAll())
						.add(linkTo(ProductController.class)
								.withSelfRel()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductModel> findById(@PathVariable Long id) {
		
		ProductModel productModel = productService.findById(id);
		
		return ResponseEntity.ok(productModel);
		
	}
	
	
	
	@PostMapping
	public ResponseEntity<ProductModel> create(@RequestBody Product product) {
		
		ProductModel productModel = productService.save(product);
		
		final URI uri = MvcUriComponentsBuilder.fromController(getClass()).build().toUri();
		
		return ResponseEntity.created(uri).body(productModel);
	
	}
	
	
	
	@PutMapping
	public ResponseEntity<ProductModel> update(@RequestBody Product product) {
		ProductModel productModel = productService.update(product);
		
		final URI uri = MvcUriComponentsBuilder.fromController(getClass()).build().toUri();
		
		return ResponseEntity.created(uri).body(productModel);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		productService.delete(id);
		
		final URI uri = MvcUriComponentsBuilder.fromController(getClass()).build().toUri();
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).location(uri).build();
		
	}
	
}
