package com.carlease.service.carleaseservice.proxy.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carlease.service.carleaseservice.CalculationOfLeaseServiceProxy;
import com.carlease.service.carleaseservice.bean.CarDetails;
import com.carlease.service.carleaseservice.bean.Customer;
import com.carlease.service.carleaseservice.exception.CarNotFoundException;
/**
 * Mapper class created in order to set values for
 * Car Entity after calling proxy service
 * @author Shruthi
 *
 */
@Component
public class CalculationOfLeaseServiceProxyMapper {
	
	private static final Logger logger = LoggerFactory.getLogger(CalculationOfLeaseServiceProxyMapper.class);

	@Autowired
	private CalculationOfLeaseServiceProxy proxy;

	/**
	 * Setting Car Details
	 * @param Customer
	 * @return CarDetails
	 */
	public CarDetails proxyCalltoRetrieveLeaseCalucationWithCustomerDetails(Customer cust) {

		CarDetails car = cust.getCar();

		try {
			if(cust!=null && car!=null) {	
				car.setCustomer(cust);
				if(!car.getModel().isEmpty() 
						&& car.getMileage()>0 && car.getDurationofLease()>0) {
					CarDetails carDetails;
					carDetails = proxy.retrieveCarDetailsWithLeaseAmount(car.getModel(), car.getMileage(), car.getDurationofLease());
						car.setCalculatedLeaseAmount(carDetails.getCalculatedLeaseAmount());
						car.setGrossPrice(carDetails.getGrossPrice());
						car.setNetPrice(carDetails.getNetPrice());
						car.setMake(carDetails.getMake());
						car.setVersion(carDetails.getVersion());
						car.setNoOfDoors(carDetails.getNoOfDoors());
					
				}
			}
		} catch (Exception e) {
			logger.info("Invalid Car Data. Hence no calculation saved");;
		}

		return car;
	}

	/**
	 * Setting Car Details
	 * @param car
	 * @return CarDetails
	 */
	public CarDetails proxyCalltoRetrieveLeaseCalucationWithCarDetails(CarDetails car) {
		CarDetails carFinalDetail = new CarDetails();
			try {
				if(car!=null) {
					CarDetails	carDetail = proxy.retrieveCarDetailsWithLeaseAmount(car.getModel()
							, car.getMileage(), car.getDurationofLease());				
					carFinalDetail.setCalculatedLeaseAmount(carDetail.getCalculatedLeaseAmount());
					carFinalDetail.setGrossPrice(carDetail.getGrossPrice());
					carFinalDetail.setNetPrice(carDetail.getNetPrice());
					carFinalDetail.setMake(carDetail.getMake());
					carFinalDetail.setVersion(carDetail.getVersion());
					carFinalDetail.setNoOfDoors(carDetail.getNoOfDoors());
					carFinalDetail.setMileage(carDetail.getMileage());
					carFinalDetail.setDurationofLease(carDetail.getDurationofLease());
					carFinalDetail.setModel(carDetail.getModel());				
				}
			} catch (Exception e) {
				throw new CarNotFoundException("Invalid Car Data");
			}
		return carFinalDetail;
	}
}
