package com.demo.productcatalogservice.service;

import java.util.List;

import com.demo.productcatalogservice.model.Product;

public interface IProductService {

	List<Product> getCatalog();
	Product addProduct(Product product);
	Product getProductById(int id);
	Product updateProduct(Product product);
}