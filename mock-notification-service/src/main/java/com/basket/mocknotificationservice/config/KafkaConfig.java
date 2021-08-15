package com.basket.mocknotificationservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.basket.mocknotificationservice.models.PriceChangeNotification;
import com.basket.mocknotificationservice.models.StockChangeNotification;

@EnableKafka
@Configuration
public class KafkaConfig {

	private String bootstrapServer = "http://localhost:9092";
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, PriceChangeNotification> productPriceChangeNotificationQueueKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PriceChangeNotification> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(PriceChangeNotification.class,false)));
        return factory;
    }
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, StockChangeNotification> productStockChangeNotificationQueueKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, StockChangeNotification> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(StockChangeNotification.class,false)));
        return factory;
    }
	
	private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializerWithJTM.class);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 25000);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 35000);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "100");
        return props;
    }
}
