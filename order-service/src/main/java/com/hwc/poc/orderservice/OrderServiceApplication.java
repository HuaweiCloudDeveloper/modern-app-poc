package com.hwc.poc.orderservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrderServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}


	@Value("${hwc.poc.inventory.url}")
	private String inventoryUrl;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Account 2 APIG URL: " + inventoryUrl);
	}
}
