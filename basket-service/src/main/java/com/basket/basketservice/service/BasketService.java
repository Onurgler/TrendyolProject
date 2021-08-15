package com.basket.basketservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basket.basketservice.domain.Basket;
import com.basket.basketservice.domain.Offer;
import com.basket.basketservice.domain.Product;
import com.basket.basketservice.repository.BasketRepository;

@Service
public class BasketService {
	
	@Autowired
	private BasketRepository basketRepository;
	
	public Basket create(Basket basket) {
		
		return basketRepository.save(basket);
	}
	
	public Basket create(String userId) {
		
		Basket basket = new Basket(userId);
		return basketRepository.save(basket);
	}
	
	public Basket findByUserId(String userId) {
		
		return basketRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException(String.format("User not found with id: %s", userId)));
	}
	
	public void addProductToUserBasket(String userId, String id, String image, String title, int quantity, float price) {

		Basket basket = basketRepository.findById(userId)
				.orElseThrow(RuntimeException::new);
		
		List<Product> products = basket.getProducts();
		Product product = new Product(id, image,title,quantity,price);
		products.add(product);
		
		basketRepository.save(basket); 
	}
	
	public void addOfferToUserBasket(String userId, String id, String title, int quantity, double price, double discountPrice) {

		Basket basket = basketRepository.findById(userId)
				.orElseThrow(RuntimeException::new);
		
		List<Offer> offers = basket.getOffers();
		Offer offer = new Offer(id,title,quantity,price,discountPrice);
		offers.add(offer);
		
		basketRepository.save(basket); 
	}
	
	public List<Basket> getAllUsersWithBasket() {

		return basketRepository.findAll();
	}
	
	public List<Basket> findByUserForProductId(String producId){
		//List<String> usersId;
		return basketRepository.findAllUsingN1ql(producId);
		
		//return usersId;
	}
	
}
