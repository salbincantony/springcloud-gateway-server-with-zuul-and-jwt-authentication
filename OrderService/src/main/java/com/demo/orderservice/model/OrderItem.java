package com.demo.orderservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="ORDER_ITEMS")
public class OrderItem
{
		@Id @GeneratedValue(strategy=GenerationType.AUTO)
		private int orderItemId;
		
		@NotNull
		private Long productId;
		
		@NotNull
		private int quantity;
		
		@NotNull
		private double price;
		
		@JsonBackReference
		@ManyToOne(cascade= CascadeType.ALL)
	    @JoinColumn(name="orderId")
	    private Order order;

		public int getOrderItemId() {
			return orderItemId;
		}

		public void setOrderItemId(int orderItemId) {
			this.orderItemId = orderItemId;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}
}
