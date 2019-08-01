package com.demo.orderservice.model;

public class CartListItem {

	int cartId;
	Product product;
	int quantity;

	public CartListItem(int cartId, Product product, int quantity) {
		super();
		this.cartId = cartId;
		this.product = product;
		this.quantity = quantity;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
