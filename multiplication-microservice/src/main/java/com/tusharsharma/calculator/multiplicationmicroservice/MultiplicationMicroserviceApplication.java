package com.tusharsharma.calculator.multiplicationmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MultiplicationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplicationMicroserviceApplication.class, args);
	}

}
