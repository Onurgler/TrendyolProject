package com.basket.mockproductservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.basket.mockproductservice.models.PriceChange;
import com.basket.mockproductservice.models.StockChange;

@EnableKafka
@Configuration
public class KafkaConfig {

	//private static final String TOPIC = "PRODUCT-PRICE-CHANGE";
	private String kafkaAddress = "http://localhost:9092";

	private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 20971469);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
        return props;
    }

    @Bean
    public KafkaTemplate<String, PriceChange> kafkaTemplatePriceChange() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
    }

    @Bean
    public KafkaTemplate<String, StockChange> kafkaTemplateStockChange() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
    }
	
}
