package com.tusharsharma.calculator.apigatewayserver.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="addition-microservice")
@RibbonClient(name="addition-microservice")
public interface AdditionMicroserviceProxy {
	
	@GetMapping("addition/expression/{operand1}/{operand2}")
	String addTheOperands(@PathVariable("operand1") String operand1, 
			@PathVariable("operand2") String operand2);

}
