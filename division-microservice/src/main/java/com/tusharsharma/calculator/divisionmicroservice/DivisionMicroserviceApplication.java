package com.tusharsharma.calculator.divisionmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DivisionMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DivisionMicroserviceApplication.class, args);
	}

}
