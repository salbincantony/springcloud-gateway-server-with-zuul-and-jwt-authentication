package com.demo.orderservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.orderservice.model.OrderItem;
@Repository
public interface IOrderItemRepository extends CrudRepository<OrderItem, Integer>{

}
