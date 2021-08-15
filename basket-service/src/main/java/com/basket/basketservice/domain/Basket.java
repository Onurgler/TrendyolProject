package com.basket.basketservice.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class Basket {
	
	@Id
	@Field
	private String userId;
	
	@Field
	private List<Product> products;
	
	@Field
	private List<Offer> offers;
	
	@Field
	private BasketInfo basketInfo;

	public Basket() {
		super();
		this.products = new ArrayList<Product>();
		this.offers = new ArrayList<Offer>();
		this.basketInfo = new BasketInfo(4.99);
		calculationBasketPrice();
	}
	
	public Basket(String userId) {
		super();
		this.userId = userId;
		this.products = new ArrayList<Product>();
		this.offers = new ArrayList<Offer>();
		this.basketInfo = new BasketInfo(4.99);
		calculationBasketPrice();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(Product product) {
		this.products.add(product);
		calculationBasketPrice();
	}
	
	public void deleteProducts(Product product) {
		this.products.remove(product);
		calculationBasketPrice();
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Offer offer) {
		this.offers.add(offer);
		calculationBasketPrice();
	}
	
	public void deleteOffers(Offer offer) {
		this.offers.remove(offer);
		calculationBasketPrice();
	}
	
	public void calculationBasketPrice() {
		double totalPrice=0;
		double totalDiscountPrice=0;
		
		for(int i=0;i<this.products.size();i++) {
			double productTotalPrice=this.products.get(i).getPrice()*this.products.get(i).getQuantity();
			for(int j=0;j<this.offers.size();j++) {
				if(this.products.get(i).getId().equals(this.offers.get(j).getId())) {
					if(this.offers.get(j).getPrice()<=productTotalPrice||this.offers.get(j).getQuantity()<=this.products.get(i).getQuantity()) {
						totalDiscountPrice=totalDiscountPrice+this.offers.get(j).getDiscountPrice();
					}
				}
				
			}
			totalPrice = totalPrice + productTotalPrice;
		}
		for(int i=0;i<this.offers.size();i++) {
			if(this.offers.get(i).getId().equals("0")) {
				if(totalPrice>=this.offers.get(i).getPrice()) {
					totalDiscountPrice=totalDiscountPrice+this.offers.get(i).getDiscountPrice();
				}
				break;
			}
		}
		this.basketInfo.setTotalPrice(totalPrice);
		this.basketInfo.setTotalDiscountPrice(totalDiscountPrice);
		this.basketInfo.setNetPrice(totalPrice-totalDiscountPrice+this.basketInfo.getCargoPrice());
		
	}

}
