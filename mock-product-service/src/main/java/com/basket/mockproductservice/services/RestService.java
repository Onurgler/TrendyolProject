package com.basket.mockproductservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.basket.mockproductservice.models.PriceChange;
import com.basket.mockproductservice.models.StockChange;

@RestController
@RequestMapping(path = "/product")
public class RestService {
	
	@Autowired
	private KafkaService kafkaService;
	
	/*
{
    "productId" : "1",
    "price" : "99.99"
} 
	 */
	@PostMapping(path = "/price-change")
    public ResponseEntity<String>  priceChange(@RequestBody PriceChange priceChange)
    {
        kafkaService.sendPriceChange(priceChange);
        return new ResponseEntity<String>("Send Price-change event", HttpStatus.OK);
    }

    @PostMapping(path = "/stock-change")
    public ResponseEntity<String>  stockChange(@RequestBody StockChange stockChange)
    {
        kafkaService.sendStockChange(stockChange);
        return new ResponseEntity<String>("Send Stock-change event", HttpStatus.OK);
    }
    
}
