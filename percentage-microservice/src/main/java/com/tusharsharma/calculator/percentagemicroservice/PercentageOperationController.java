package com.tusharsharma.calculator.percentagemicroservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PercentageOperationController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("percentage/expression/{operand}")
	String percentOperation(@PathVariable String operand) {
		
		try {
			double doubleOperand = Double.parseDouble(operand);
			logger.info("Executing Percentage Microservice : {}%", operand);
			return Double.toString(doubleOperand / 100);
		} catch (Exception e) {
			logger.error("An exception occurred",e);
			return "Invalid input";
		}
	}

}
