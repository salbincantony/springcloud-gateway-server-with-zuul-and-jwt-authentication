package com.demo.productcatalogservice.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class ProductCatalog {

	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
