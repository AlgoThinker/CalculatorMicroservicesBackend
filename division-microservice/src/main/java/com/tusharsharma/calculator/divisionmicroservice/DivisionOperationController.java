package com.tusharsharma.calculator.divisionmicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DivisionOperationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("division/expression/{operand1}/{operand2}")
	String divideTheOperands(@PathVariable String operand1, @PathVariable String operand2) {

		try {
			double doubleOperand1 = Double.parseDouble(operand1);
			double doubleOperand2 = Double.parseDouble(operand2);
			logger.info("Executing Division Microservice : {} / {}", operand1, operand2);
			return Double.toString(doubleOperand1 / doubleOperand2);
		} catch (Exception e) {
			logger.error("An exception occurred",e);
			return "Invalid input";
		}
	}

}
