package com.carlease.service.carleasecompanyservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.carlease.service.carleasecompanyservice.bean.CarDetailsFromCompany;
import com.carlease.service.carleasecompanyservice.repo.CarDetailsFromCompanyRepository;

/**
 * Test Class for testing the Controller and the Utility
 * @author Shruthi
 *
 */
@SpringBootTest
public class CarLeaseCompanyServiceApplicationTests {
	
	@Autowired
	CarCompanyCalculationController carDetailsController;
	
	@MockBean
	CarDetailsFromCompanyRepository repository;
	
	@Test
	void contextLoads() {
	}

	/**
	 * Test case for checking utility 
	 */
	@Test
	public void checkLeaseCalculation() {
	double result = 239.76;
		long mileage = 45000;
		int duration = 60;
		double intrate = 4.5;
		double netprice = 63000.0;
		
		double leaseamt = LeaseCalculator.calculateLeaseAmount(mileage, duration, intrate, netprice);
		assertEquals(result, leaseamt, leaseamt);
	}
	
	/**
	 * test case for checking the controller functionality
	 */
	@Test
	public void testRetrieveCarDetailsWithLeaseAmount() {
		
		CarDetailsFromCompany car = buildCarDetails();
		/*Mocking call to repository*/
		when(repository.findByModel(car.getModel())).thenReturn(car);
		
		CarDetailsFromCompany resultout;
		try {
			resultout = carDetailsController.retrieveCarDetailsWithLeaseAmount(car.getModel()
					,car.getMileage(),car.getDurationofLease());
			assertEquals(car.getMileage(), resultout.getMileage(), car.getMileage());
			assertNotNull(resultout);
			assertNotNull(resultout.getCalculatedLeaseAmount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Used to build mocked data for CarDetails
	 * 
	 * @return CarDetailsFromCompany
	 */
	public CarDetailsFromCompany buildCarDetails() {
		
		CarDetailsFromCompany cardetails = new CarDetailsFromCompany();

		cardetails.setDurationofLease(30);
		cardetails.setGrossPrice(23000);
		cardetails.setInterestRate(2.5);
		cardetails.setMake(2018);
		cardetails.setMileage(23000);
		cardetails.setModel("Hyundai-Bayon");
		cardetails.setNetPrice(20000);
		cardetails.setNoOfDoors(5);
		cardetails.setVersion(2.1f);
		
		return cardetails;
	}

}

