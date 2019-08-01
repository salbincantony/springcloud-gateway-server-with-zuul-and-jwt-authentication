package com.demo.productcatalogservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.productcatalogservice.model.Product;
import com.demo.productcatalogservice.model.ProductCatalog;
import com.demo.productcatalogservice.service.IProductService;

@RestController
public class ProductCatalogResource {
	
		@Autowired
		IProductService productService;
		
		@Autowired
		ProductCatalog productCatalog;

		@GetMapping(value = "/products")
		@CrossOrigin
		@ResponseBody
		public ProductCatalog getCatalog() {
			productCatalog.setProducts(productService.getCatalog()); 
			return productCatalog;
		}
		@GetMapping(value = "/products/{id}")
		@CrossOrigin
		public Product getProductById(@PathVariable("id") int id) {
			
			return productService.getProductById(id);			
		}
		@PostMapping(value = "/products")
		@CrossOrigin
		public ResponseEntity<String> addProduct(@RequestBody Product product) {
			productService.addProduct(product);
			 return new ResponseEntity<String>("Product " +product.getName() + " added successfully", HttpStatus.OK);
		}
		@PutMapping(value = "/products")
		@CrossOrigin
		public ResponseEntity<String> updateProduct(@RequestBody Product product) {
			productService.updateProduct(product);
			return new ResponseEntity<String>("Product " +product.getName() + " updated successfully" , HttpStatus.OK);
		}
		
		
}
