package com.demo.productcatalogservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.productcatalogservice.model.Product;

@Repository
public interface IProductRepository extends CrudRepository<Product, Integer>{

}
