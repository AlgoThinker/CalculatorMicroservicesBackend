package com.tusharsharma.calculator.percentagemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PercentageMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PercentageMicroserviceApplication.class, args);
	}

}
