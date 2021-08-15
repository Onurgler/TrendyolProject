package com.basket.basketservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.basket.basketservice.domain.Basket;
import com.basket.basketservice.domain.Offer;
import com.basket.basketservice.domain.Product;
import com.basket.basketservice.domain.RequestOffer;
import com.basket.basketservice.domain.RequestProduct;
import com.basket.basketservice.service.BasketService;


@RestController
@RequestMapping("/basket")
public class BasketController {
	/* EN SON DOLDUR */
	@Autowired
	private BasketService basketService;
	
	@GetMapping()
	public List<Basket> getAllBasket(){
		return basketService.getAllUsersWithBasket();
	}
	
	@GetMapping("/{id}")
	public Basket getBasket(@PathVariable("id") String id){
		return basketService.findByUserId(id);
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<String> addBasket(@RequestBody Basket basket)
    {	
		//ResponseEntity<String> responseEntity;
		try {
			basketService.findByUserId(basket.getUserId());
			return new ResponseEntity<String>("Basket is not added because is exist: "+basket.getUserId(), HttpStatus.OK);
		}
		catch (Exception e) {
			basketService.create(basket);
			
		}
		basket.setOffers(new Offer("0","100 TL uzeri alisveriste kargo ucretsiz",0,100.00,4.99));
		basketService.create(basket);
		return new ResponseEntity<String>("Basket is added: "+basket.getUserId(), HttpStatus.OK);
    }
	
	@PostMapping(path = "/add-product")
    public ResponseEntity<String>  addProduct(@RequestBody RequestProduct requestProduct)
    {	
		Basket basket;
		try {
			basket=basketService.findByUserId(requestProduct.getUserId());
			List<Product> products=basket.getProducts();
			for(int i=0;i<products.size();i++) {
				if(products.get(i).getId().equals(requestProduct.getProduct().getId())) {
					products.get(i).setQuantity(products.get(i).getQuantity()+requestProduct.getProduct().getQuantity());
					basket.calculationBasketPrice();
					basketService.create(basket);
					return new ResponseEntity<String>("Product is exist, quantity increased -> UserId:"+requestProduct.getUserId()+" ; "+requestProduct.getProduct().toString(), HttpStatus.OK);
				}
			}
			products.add(requestProduct.getProduct());
			basket.calculationBasketPrice();
			basketService.create(basket);
			return new ResponseEntity<String>("Product is added -> UserId:"+requestProduct.getUserId()+" ; "+requestProduct.getProduct().toString(), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Basket is not exist: "+requestProduct.getUserId(), HttpStatus.OK);
		}
    }
	
	@PostMapping(path = "/delete-product")
    public ResponseEntity<String>  deleteProduct(@RequestBody RequestProduct requestProduct)
    {	
		Basket basket;
		try {
			basket=basketService.findByUserId(requestProduct.getUserId());
			List<Product> products=basket.getProducts();
			for(int i=0;i<products.size();i++) {
				if(products.get(i).getId().equals(requestProduct.getProduct().getId())) {
					products.get(i).setQuantity(products.get(i).getQuantity()-requestProduct.getProduct().getQuantity());
					if(products.get(i).getQuantity()<=0)
						products.remove(i);
					basket.calculationBasketPrice();
					basketService.create(basket);
					return new ResponseEntity<String>("Product is exist, quantity decreased or removed -> UserId:"+requestProduct.getUserId()+" ; "+requestProduct.getProduct().toString(), HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>("Product is not exist -> UserId:"+requestProduct.getUserId(), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Basket is not exist: "+requestProduct.getUserId(), HttpStatus.OK);
		}
    }
	
	@PostMapping(path = "/add-offer")
    public ResponseEntity<String>  addOffer(@RequestBody RequestOffer requestOffer)
    {	
		Basket basket;
		try {
			basket=basketService.findByUserId(requestOffer.getUserId());
			List<Offer> offers=basket.getOffers();
			for(int i=0;i<offers.size();i++) {
				if(offers.get(i).getId().equals(requestOffer.getOffer().getId())) {
					offers.remove(i);
					offers.add(requestOffer.getOffer());
					basket.calculationBasketPrice();
					basketService.create(basket);
					return new ResponseEntity<String>("Offer is changed -> UserId:"+requestOffer.getUserId()+" ; "+requestOffer.getOffer().toString(), HttpStatus.OK);
				}
			}
			offers.add(requestOffer.getOffer());
			basket.calculationBasketPrice();
			basketService.create(basket);
			return new ResponseEntity<String>("Offer is added -> UserId:"+requestOffer.getUserId()+" ; "+requestOffer.getOffer().toString(), HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Basket is not exist: "+requestOffer.getUserId(), HttpStatus.OK);
		}
    }
}
