package com.demo.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.orderservice.CatalogException;
import com.demo.orderservice.model.Order;
import com.demo.orderservice.model.OrderDetail;
import com.demo.orderservice.model.OrderItems;
import com.demo.orderservice.model.Product;
import com.demo.orderservice.service.IOrderService;



@RestController
public class OrderResource {

	@Autowired
	IOrderService orderService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${ip.productCatalog}")
    private String ipProductCatalog;
	
	@GetMapping(path = "/orders")
    public OrderItems getMyOrders() {
        List<Order> ordersList = orderService.getOrders();
        OrderItems orderItemsList = orderService.getOrderList(ordersList);       
        return orderItemsList;
    }
	
	
	@Autowired
	Product product;
		
	@GetMapping(path = "/orders/{orderId}")
    public OrderDetail getMyOrdersById(@PathVariable Integer orderId) {
        Order orders = orderService.getOrderById(orderId);
        OrderDetail orderDetails = new OrderDetail();
        orderDetails =orderService.getOrder(orders);        
        return orderDetails;
    }

	
	@PostMapping(path = "/orders/{userid}")
	public ResponseEntity<String> placeOrders(@PathVariable String userid) {
		if (userid.isEmpty()) {
			return new ResponseEntity<String>("Invalid User", HttpStatus.UNAUTHORIZED);
		}
		try {
		orderService.placeOrder(userid);
		}catch(CatalogException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
		return new ResponseEntity<String>("Order Placed successfully", HttpStatus.OK);

	}
	
	@DeleteMapping("/orders/{id}")
	public ResponseEntity<String> deleteOrderItems(@PathVariable Integer id) {
		orderService.cancelOrder(id);
		return new ResponseEntity<String>("Order Deleted successfully", HttpStatus.OK);
	}

}