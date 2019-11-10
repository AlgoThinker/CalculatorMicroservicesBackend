package com.tusharsharma.calculator.subtractionmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SubtractionMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubtractionMicroserviceApplication.class, args);
	}

}
