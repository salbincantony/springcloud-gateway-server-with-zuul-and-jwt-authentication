package com.demo.orderservice.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class OrderDetail {
    
	private int orderId;
	
	private double totalprice;
	
	private String createdBy;
	
	private Date createdOn;

     Set<OrderItemDetail> orderItemDetail;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Set<OrderItemDetail> getOrderItemDetail() {
		return orderItemDetail;
	}

	public void setOrderItemDetail(Set<OrderItemDetail> orderItemDetail) {
		this.orderItemDetail = orderItemDetail;
	}

	

	
}