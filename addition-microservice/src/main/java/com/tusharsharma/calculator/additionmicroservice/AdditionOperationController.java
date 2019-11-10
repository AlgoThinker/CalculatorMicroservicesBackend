package com.tusharsharma.calculator.additionmicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionOperationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Environment environment;

	@GetMapping("addition/expression/{operand1}/{operand2}")
	String addTheOperands(@PathVariable String operand1, @PathVariable String operand2) {
		try {
			double doubleOperand1 = Double.parseDouble(operand1);
			double doubleOperand2 = Double.parseDouble(operand2);
			logger.info("Executing Addtion Microservice : {} + {}", operand1, operand2);
			//below log is to demonstrate the load balancing feature across multiple instances of a single microservice
			logger.info("Port on which the request was executed"+  environment.getProperty("local.server.port"));
			return Double.toString(doubleOperand1 + doubleOperand2);
		} catch (Exception e) {
			logger.error("An exception occurred",e);
			return "Invalid input";
		}
	}

}
