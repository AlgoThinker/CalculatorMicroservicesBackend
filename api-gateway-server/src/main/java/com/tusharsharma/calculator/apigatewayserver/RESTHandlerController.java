package com.tusharsharma.calculator.apigatewayserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tusharsharma.calculator.apigatewayserver.proxies.AdditionMicroserviceProxy;
import com.tusharsharma.calculator.apigatewayserver.proxies.DivisionMicroserviceProxy;
import com.tusharsharma.calculator.apigatewayserver.proxies.MultiplicationMicroserviceProxy;
import com.tusharsharma.calculator.apigatewayserver.proxies.PercentageMicroserviceProxy;
import com.tusharsharma.calculator.apigatewayserver.proxies.SubtractionMicroserviceProxy;

@RestController
public class RESTHandlerController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdditionMicroserviceProxy additionProxy;
	@Autowired
	private SubtractionMicroserviceProxy subtractionProxy;
	@Autowired
	private MultiplicationMicroserviceProxy multiplicationProxy;
	@Autowired
	private DivisionMicroserviceProxy divisionProxy;
	@Autowired
	private PercentageMicroserviceProxy percentageProxy;

	@PostMapping("api-gateway")
	String performOperation(@RequestBody String expression) {

		return null;
		
		
	}

}
