package com.basket.basketservice.domain;

public class StockChange {

	private String productId;
	private int stock;
	
	public StockChange() {
		super();
	}

	public StockChange(String productId, int stock) {
		super();
		this.productId = productId;
		this.stock = stock;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
