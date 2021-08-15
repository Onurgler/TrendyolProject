package com.basket.mockproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MockProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockProductServiceApplication.class, args);
	}

	public void run(String... args) throws Exception {
        System.out.println("Mock Product Service starts!");
    }
}
