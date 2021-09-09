package com.carlease.service.carleaseservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.carlease.service.carleaseservice.bean.CarDetails;

@FeignClient(name="car-lease-company-service")
@RibbonClient(name = "car-lease-company-service")
public interface CalculationOfLeaseServiceProxy {

	/**
	 * Proxy service to call car-lease-company-service
	 * @param model
	 * @param mileage
	 * @param duration
	 * @return
	 */
	@GetMapping("/CarDetailsWithLeaseCalculation/model/{model}/mileage/{mileage}/duration/{duration}")
	public CarDetails retrieveCarDetailsWithLeaseAmount(@PathVariable String model, 
			@PathVariable Long mileage, @PathVariable Integer duration);
}
