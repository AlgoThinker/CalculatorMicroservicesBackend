package com.tusharsharma.calculator.subtractionmicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubtractionOperationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("subtraction/expression/{operand1}/{operand2}")
	String subtractTheOperands(@PathVariable String operand1, @PathVariable String operand2) {
		try {
			double doubleOperand1 = Double.parseDouble(operand1);
			double doubleOperand2 = Double.parseDouble(operand2);
			logger.info("Executing Subtraction Microservice : {} - {}", operand1, operand2);
			return Double.toString(doubleOperand1 - doubleOperand2);
		} catch (Exception e) {
			logger.error("An exception occurred",e);
			return "Invalid input";
		}
	}

}
