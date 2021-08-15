package com.basket.mockproductservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.basket.mockproductservice.models.PriceChange;
import com.basket.mockproductservice.models.StockChange;

@Service
public class KafkaService {

	@Autowired
    private final KafkaTemplate<String, PriceChange> kafkaTemplatePriceChange;
	@Autowired
    private final KafkaTemplate<String, StockChange> kafkaTemplateStockChange;

    @Value("${spring.kafka.topic.product-price-change}")
    private String productPriceChangeDestination;
    
    @Value("${spring.kafka.topic.product-stock-change}")
    private String productStockChangeDestination;
    
    public KafkaService(KafkaTemplate<String, PriceChange> kafkaTemplatePriceChange,
            			KafkaTemplate<String, StockChange> kafkaTemplateStockChange) {
    		this.kafkaTemplatePriceChange = kafkaTemplatePriceChange;
    		this.kafkaTemplateStockChange = kafkaTemplateStockChange;
    }

    public void sendStockChange(StockChange stockChange) {
        Message<StockChange> kafkaMessage = MessageBuilder
                .withPayload(stockChange)
                .setHeader(KafkaHeaders.TOPIC, productStockChangeDestination)
                .build();
        System.out.println(stockChange.getProductId()+" <--> "+stockChange.getStock());
        System.out.println("Stock Change Mesaj Gönderildi");
        kafkaTemplateStockChange.send(kafkaMessage);
    }
    
    public void sendPriceChange(PriceChange priceChange) {
        Message<PriceChange> kafkaMessage = MessageBuilder
                .withPayload(priceChange)
                .setHeader(KafkaHeaders.TOPIC, productPriceChangeDestination)
                .build();
        System.out.println(priceChange.getProductId()+" <--> "+priceChange.getPrice());
        System.out.println("Price Change Mesaj Gönderildi");
        kafkaTemplatePriceChange.send(kafkaMessage);
    }
	
}
