package com.basket.basketservice.domain;

import org.springframework.data.couchbase.core.mapping.Field;

public class BasketInfo {

	@Field
	private double totalPrice;
	
	@Field
	private double cargoPrice;
	
	@Field
	private double totalDiscountPrice;
	
	@Field
	private double netPrice;

	public BasketInfo() {
		super();
	}

	public BasketInfo(double cargoPrice) {
		super();
		this.cargoPrice = cargoPrice;
	}

	public BasketInfo(double totalPrice, double cargoPrice, double totalDiscountPrice, double netPrice) {
		super();
		this.totalPrice = totalPrice;
		this.cargoPrice = cargoPrice;
		this.totalDiscountPrice = totalDiscountPrice;
		this.netPrice = netPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getCargoPrice() {
		return cargoPrice;
	}

	public void setCargoPrice(double cargoPrice) {
		this.cargoPrice = cargoPrice;
	}

	public double getTotalDiscountPrice() {
		return totalDiscountPrice;
	}

	public void setTotalDiscountPrice(double totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}

	public double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}

	@Override
	public String toString() {
		return "BasketInfo [totalPrice=" + totalPrice + ", cargoPrice=" + cargoPrice + ", totalDiscountPrice="
				+ totalDiscountPrice + ", netPrice=" + netPrice + "]";
	}

}
