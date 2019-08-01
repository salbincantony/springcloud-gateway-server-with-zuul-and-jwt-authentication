package com.demo.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.orderservice.model.Order;

@Repository
public interface IOrderRepository extends CrudRepository<Order, Integer>{


    @Query("SELECT max(o.orderId) FROM Order o")
    Integer findOrderId();
    
    @Query("SELECT o FROM Order o where o.orderId=:id")
    Order findOrderByOrderId(@Param("id") int id);
	
}
