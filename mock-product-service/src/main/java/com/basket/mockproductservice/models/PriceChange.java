package com.basket.mockproductservice.models;

public class PriceChange {

	private String productId;
	private double price;
	
	public PriceChange() {
		super();
	}

	public PriceChange(String productId, double price) {
		super();
		this.productId = productId;
		this.price = price;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
