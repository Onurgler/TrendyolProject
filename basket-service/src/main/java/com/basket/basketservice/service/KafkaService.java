package com.basket.basketservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.basket.basketservice.domain.Basket;
import com.basket.basketservice.domain.PriceChange;
import com.basket.basketservice.domain.PriceChangeNotification;
import com.basket.basketservice.domain.StockChange;
import com.basket.basketservice.domain.StockChangeNotification;

@Service
public class KafkaService {
	
	@Autowired
    private final KafkaTemplate<String, PriceChangeNotification> kafkaTemplatePriceChangeNotification;
	@Autowired
    private final KafkaTemplate<String, StockChangeNotification> kafkaTemplateStockChangeNotification;

    @Value("${spring.kafka.topic.product-price-change-notification}")
    private String productPriceChangeDestination;
    
    @Value("${spring.kafka.topic.product-stock-change-notification}")
    private String productStockChangeDestination;
    
    @Autowired
	BasketService basketService;

	public KafkaService(KafkaTemplate<String, PriceChangeNotification> kafkaTemplatePriceChangeNotification,
						KafkaTemplate<String, StockChangeNotification> kafkaTemplateStockChangeNotification) {
    		this.kafkaTemplatePriceChangeNotification = kafkaTemplatePriceChangeNotification;
    		this.kafkaTemplateStockChangeNotification = kafkaTemplateStockChangeNotification;
    }
	
	
	@KafkaListener(
            topics = "${spring.kafka.topic.product-stock-change}",
            groupId = "product-stock-change-group",
            clientIdPrefix = "product-stock-change-group",
            containerFactory = "productStockChangeQueueKafkaListenerContainerFactory"
    )
	public void changeStock(StockChange stockChange) {
		String productId=stockChange.getProductId();
		
		List<Basket> baskets = basketService.findByUserForProductId(productId);
		List<String> userIds = new ArrayList<String>();
		for(int i=0;i<baskets.size();i++) {
			userIds.add(baskets.get(i).getUserId());
		}
		StockChangeNotification stockChangeNotification = new StockChangeNotification(userIds,stockChange.getStock());
		sendStockChange(stockChangeNotification);
		
		System.out.println("Send Notification Stock Change: "+productId+" <--> "+stockChange.getStock());	
	}
	
	@KafkaListener(
            topics = "${spring.kafka.topic.product-price-change}",
            groupId = "product-price-change-group",
            clientIdPrefix = "product-price-change-group",
            containerFactory = "productPriceChangeQueueKafkaListenerContainerFactory"
    )
	public void changePrice(PriceChange priceChange) {
		String productId=priceChange.getProductId();
		
		List<Basket> baskets = basketService.findByUserForProductId(productId);
		List<String> userIds = new ArrayList<String>();
		for(int i=0;i<baskets.size();i++) {
			userIds.add(baskets.get(i).getUserId());
		}
		PriceChangeNotification priceChangeNotification = new PriceChangeNotification(userIds,priceChange.getPrice());
		sendPriceChange(priceChangeNotification);
		
		System.out.println("Send Notification Price Change: "+productId+" <--> "+priceChange.getPrice());	
	}
	
	public void sendStockChange(StockChangeNotification stockChangeNotification) {
        Message<StockChangeNotification> kafkaMessage = MessageBuilder
                .withPayload(stockChangeNotification)
                .setHeader(KafkaHeaders.TOPIC, productStockChangeDestination)
                .build();
        System.out.println("Size:"+stockChangeNotification.getUserIds().size()+" <--> "+stockChangeNotification.getStock());
        System.out.println("Stock Change Notification Mesaj Gönderildi");
        kafkaTemplateStockChangeNotification.send(kafkaMessage);
    }
	
	public void sendPriceChange(PriceChangeNotification priceChangeNotification) {
        Message<PriceChangeNotification> kafkaMessage = MessageBuilder
                .withPayload(priceChangeNotification)
                .setHeader(KafkaHeaders.TOPIC, productPriceChangeDestination)
                .build();
        System.out.println("Size:"+priceChangeNotification.getUserIds().size()+" <--> "+priceChangeNotification.getPrice());
        System.out.println("Price Change Mesaj Gönderildi");
        kafkaTemplatePriceChangeNotification.send(kafkaMessage);
    }
	
}
