package com.tusharsharma.calculator.apigatewayserver.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="percentage-microservice")
@RibbonClient(name="percentage-microservice")
public interface PercentageMicroserviceProxy {
	
	@GetMapping("percentage/expression/{operand}")
	String percentOperation(@PathVariable("operand") String operand);

}
