package com.demo.orderservice.model;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class CartList {

	List<Integer> prodID;

	public List<Integer> getProdID() {
		return prodID;
	}

	public void setProdID(List<Integer> prodID) {
		this.prodID = prodID;
	}

	
}
