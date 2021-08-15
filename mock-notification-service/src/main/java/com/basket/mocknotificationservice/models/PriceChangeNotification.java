package com.basket.mocknotificationservice.models;

import java.util.List;

public class PriceChangeNotification {

	private List<String> userIds;
	
	private double price;

	public PriceChangeNotification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PriceChangeNotification(List<String> userIds, double price) {
		super();
		this.userIds = userIds;
		this.price = price;
	}

	public List<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<String> userIds) {
		this.userIds = userIds;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
