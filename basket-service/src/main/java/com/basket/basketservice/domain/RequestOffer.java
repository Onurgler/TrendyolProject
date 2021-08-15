package com.basket.basketservice.domain;

public class RequestOffer {

	private String userId;
	
	private Offer offer;

	public RequestOffer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestOffer(String userId, Offer offer) {
		super();
		this.userId = userId;
		this.offer = offer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	
	
}
