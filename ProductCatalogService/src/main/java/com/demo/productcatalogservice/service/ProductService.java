package com.demo.productcatalogservice.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.demo.productcatalogservice.model.Product;
import com.demo.productcatalogservice.repository.IProductRepository;


@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ProductService implements IProductService {

	@Autowired
	IProductRepository productRepo;

	@Override
	public List<Product> getCatalog(){
		return (List<Product>) productRepo.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public Product getProductById(int id) {
		return productRepo.findById(id).get();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}
}
