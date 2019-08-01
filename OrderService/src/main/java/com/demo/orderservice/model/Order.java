package com.demo.orderservice.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ORDER_DETAIL")
public class Order {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int orderId;
	
	@NotNull
	private double totalprice;
	
	@NotNull
	private String createdBy;
	
	@NotNull
	private Date createdOn;
	
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER,mappedBy="order",cascade = CascadeType.ALL)
    private Set<OrderItem> orderItem;

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

	public Set<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(Set<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	
}
