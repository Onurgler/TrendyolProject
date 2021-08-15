package com.basket.mocknotificationservice.models;

import java.util.List;

public class StockChangeNotification {
		
	private List<String> userIds;
	
	private int stock;

	public StockChangeNotification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockChangeNotification(List<String> userIds, int stock) {
		super();
		this.userIds = userIds;
		this.stock = stock;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
