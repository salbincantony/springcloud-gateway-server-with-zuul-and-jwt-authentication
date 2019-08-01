package com.demo.orderservice.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class OrderItems {

     
    private List<OrderDetail> OrderDetails;

 

    public List<OrderDetail> getOrderList() {
        return OrderDetails;
    }

 

    public void setOrderList(List<OrderDetail> orderList) {
        this.OrderDetails = orderList;
    }
    
    
}
 
