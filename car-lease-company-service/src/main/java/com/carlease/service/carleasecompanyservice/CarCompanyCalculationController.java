package com.carlease.service.carleasecompanyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.carlease.service.carleasecompanyservice.bean.CarDetailsFromCompany;
import com.carlease.service.carleasecompanyservice.exception.CarNotFoundException;
import com.carlease.service.carleasecompanyservice.repo.CarDetailsFromCompanyRepository;

/**
 * RestController for getting Car details and also lease amount
 * @author Shruthi
 */
@RestController
public class CarCompanyCalculationController {

	@Autowired
	private CarDetailsFromCompanyRepository repository;


	/**The controller method built to get the corresponding car details for a customer
	 * and also calculate the lease amount for the car bounded with customer
	 * @throws Exception 
	 * @pathparam car model
	 * @pathparam Car mileage in km/year 
	 * @pathParam Duration of lease
	 * @returns car details along with calculated lease amount.
	 */
	@GetMapping("/CarDetailsWithLeaseCalculation/model/{model}/mileage/{mileage}/duration/{duration}")
	public CarDetailsFromCompany retrieveCarDetailsWithLeaseAmount(@PathVariable String model, 
			 @PathVariable Long mileage, @PathVariable Integer duration) throws Exception {
		
		if(model.length()==0 || mileage < 0 || duration < 0) {
			throw new Exception("model/mileage/duration is not correct. Please pass right values");
		}
		
		CarDetailsFromCompany carDetails = repository
				.findByModel(model);
		if(carDetails != null) {
			double leaseAmount = LeaseCalculator.calculateLeaseAmount(mileage, duration, carDetails.getInterestRate()
					,carDetails.getNetPrice());
			carDetails.setCalculatedLeaseAmount(leaseAmount);
			carDetails.setDurationofLease(duration);
			carDetails.setMileage(mileage);
			
		}
		else {
			throw new CarNotFoundException("The model number is not present");
			 }
		
		return carDetails;
	}

}
