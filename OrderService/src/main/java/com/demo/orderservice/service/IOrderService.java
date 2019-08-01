package com.demo.orderservice.service;

import java.util.List;

import com.demo.orderservice.model.Order;
import com.demo.orderservice.model.OrderDetail;
import com.demo.orderservice.model.OrderItems;

public interface IOrderService {
	List<Order> getOrders();
	void placeOrder(String userId);
	void cancelOrder(int orderID);
	Order getOrderById(int orderId);
	OrderDetail getOrder(Order order);
	OrderItems getOrderList(List<Order> orders);
}
