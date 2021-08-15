package com.basket.basketservice.domain;

import org.springframework.data.couchbase.core.mapping.Field;

public class Offer {

	@Field
	private String id;
	
	@Field
	private String title;
	
	@Field
	private int quantity;
	
	@Field
	private double price;
	
	@Field
	private double discountPrice;


	public Offer(String id, String title, int quantity, double price, double discountPrice) {
		super();
		this.id = id;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
		this.discountPrice = discountPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", quantity=" + quantity + ", price=" + price
				+ ", discountPrice=" + discountPrice + "]";
	}
	
}
