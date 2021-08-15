package com.basket.basketservice.domain;

public class RequestProduct {

	private String userId;
	
	private Product product;

	public RequestProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestProduct(String userId, Product product) {
		super();
		this.userId = userId;
		this.product = product;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
