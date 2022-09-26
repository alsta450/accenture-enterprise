package com.accenture.enterprise.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.accenture.enterprise.controller.hateoas.ProductModel;
import com.accenture.enterprise.entities.Product;
import com.accenture.enterprise.repository.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductModel> findAll() {
		List<ProductModel> productModels = new ArrayList<>();

		productRepository.findAll().iterator()
				.forEachRemaining(product -> productModels.add(new ProductModel(product)));

		return productModels;
	}

	public ProductModel findById(Long id) {
		return productRepository.findById(id).map(product -> new ProductModel(product))
				.orElseThrow(() -> new IllegalArgumentException());
	}

	public ProductModel save(Product product) {
		productRepository.save(product);
		return new ProductModel(product);
	}

	public ProductModel update(Product product) {
		if (productRepository.existsById(product.getId())) {
			productRepository.save(product);
			return new ProductModel(product);
		}
		throw new IllegalArgumentException();
	}

	public void delete(Long id) {
		productRepository.deleteById(id);

	}

}
