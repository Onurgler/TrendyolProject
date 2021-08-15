package com.basket.mocknotificationservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.basket.mocknotificationservice.models.PriceChangeNotification;
import com.basket.mocknotificationservice.models.StockChangeNotification;

@Service
public class KafkaService {
	
	@Autowired
	MailService mailService;
	
	@KafkaListener(
            topics = "${spring.kafka.topic.product-stock-change-notification}",
            groupId = "product-stock-change-notification-group",
            clientIdPrefix = "product-stock-change-notification-group",
            containerFactory = "productStockChangeNotificationQueueKafkaListenerContainerFactory"
    )
	public void changeStock(StockChangeNotification stockChangeNotification) {
		List<String> userIds=stockChangeNotification.getUserIds();
		String ids="";
		for(int i=0;i<userIds.size();i++) 
			ids=ids+" , "+userIds.get(i);

		if(stockChangeNotification.getStock()==0)
			mailService.sendFromGMail("45onurguler45@gmail.com", "Stok bitti!", String.format("Sepetinizdeki ürünün stoğu malesef tükendi. %s idli kisilere gönderildi.",ids));
		else if(stockChangeNotification.getStock()<=3)
			mailService.sendFromGMail("45onurguler45@gmail.com", "Stok azaldı!", String.format("Sepetinizdeki ürünün stoğu azalıyor. %s idli kisilere gönderildi.",ids));
		System.out.println("Send Stock Notification "+userIds.size()+" users<--> "+stockChangeNotification.getStock());	
	}
	
	@KafkaListener(
            topics = "${spring.kafka.topic.product-price-change-notification}",
            groupId = "product-price-change-notification-group",
            clientIdPrefix = "product-price-change-notification-group",
            containerFactory = "productPriceChangeNotificationQueueKafkaListenerContainerFactory"
    )
	public void changePrice(PriceChangeNotification priceChangeNotification) {
		List<String> userIds=priceChangeNotification.getUserIds();
		String ids="";
		for(int i=0;i<userIds.size();i++) 
			ids=ids+" , "+userIds.get(i);
		
		mailService.sendFromGMail("45onurguler45@gmail.com", "Fiyat düştü!", String.format("Sepetinizdeki ürünün fiyatı düştü. %s kisiye gönderildi.",ids));
		
		System.out.println("Send Price Notification "+userIds.size()+" <--> "+priceChangeNotification.getPrice());	
	}

}
