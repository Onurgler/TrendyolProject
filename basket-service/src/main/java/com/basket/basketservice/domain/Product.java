package com.basket.basketservice.domain;

import org.springframework.data.couchbase.core.mapping.Field;

public class Product {

	@Field
	private String id;
	
	@Field
	private String image;
	
	@Field
	private String title;
	
	@Field
	private int quantity;
	
	@Field
	private double price;

	public Product(String id, String image, String title, int quantity, double price) {
		super();
		this.id = id;
		this.image = image;
		this.title = title;
		this.quantity = quantity;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", image=" + image + ", title=" + title + ", quantity=" + quantity + ", price="
				+ price + "]";
	}
	
}
