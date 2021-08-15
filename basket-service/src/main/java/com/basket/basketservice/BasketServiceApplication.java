package com.basket.basketservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.basket.basketservice.domain.Basket;
import com.basket.basketservice.domain.Offer;
import com.basket.basketservice.domain.Product;
import com.basket.basketservice.service.BasketService;

@SpringBootApplication
public class BasketServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BasketServiceApplication.class, args);
	}
	
	@Autowired
	private BasketService basketService;
	
	@Override
	public void run(String... args) throws Exception {
		
		Basket basket= new Basket ("1");
		basket.setProducts(new Product("1","Masaimageurl.png","Masa",5,195.25));
		basket.setProducts(new Product("2","Sandalyeimageurl.png","Sandalye",10,65.45));
		basket.setProducts(new Product("3","MasaOrtusuimageurl.png","MasaOrtusu",3,20.00));
		
		basket.setOffers(new Offer("0","100 TL uzeri alisveriste kargo ucretsiz",0,100.00,4.99));
		basket.setOffers(new Offer("1","3 adet ustu veya 500 TL uzeri Masa Aliminda 50 TL Indirim",3,500.00,50.00));
		basket.setOffers(new Offer("2","15 adet ustu veya 500 TL uzeri Sandalye Aliminda 50 TL Indirim",15,500.00,50.00));
		basketService.create(basket);

	}

}
