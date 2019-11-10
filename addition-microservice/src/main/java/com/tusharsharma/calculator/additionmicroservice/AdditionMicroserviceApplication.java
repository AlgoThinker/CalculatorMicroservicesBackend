package com.tusharsharma.calculator.additionmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdditionMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdditionMicroserviceApplication.class, args);
	}

}
