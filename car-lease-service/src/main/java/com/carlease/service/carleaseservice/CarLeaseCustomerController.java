package com.carlease.service.carleaseservice;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carlease.service.carleaseservice.bean.CarDetails;
import com.carlease.service.carleaseservice.bean.Customer;
import com.carlease.service.carleaseservice.exception.CustomerNotFoundException;
import com.carlease.service.carleaseservice.proxy.mapper.CalculationOfLeaseServiceProxyMapper;
import com.carlease.service.carleaseservice.repository.CarRepository;
import com.carlease.service.carleaseservice.repository.CustomerRepository;

@RestController
public class CarLeaseCustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CarLeaseCustomerController.class);
	
	@Autowired
	private CustomerRepository customerrepo;
	
	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	CalculationOfLeaseServiceProxyMapper proxymapper;
	
	/**
	 * Get method to get customer details based on 
	 * customer id
	 * @param id
	 * @return Customer
	 */
	@GetMapping("/CarLease/customers/{id}")
	public Customer getcustomerByid(@PathVariable Integer id) {
		Customer customer = customerrepo
				.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer data missing/ Not Found"));	
		return customer;
	}
	
	
	/**
	 * Method to retieve all customers
	 * @return List<Customers>
	 */
	@GetMapping("/CarLease/Customers")
	public List<Customer> getAllCustomers(){
		List<Customer> coustomers = customerrepo.findAll();
		return coustomers;			
	}
	
	/**
	 * Method to delete a customer
	 * with specific customer id
	 * @param id
	 */
	
	@DeleteMapping("/CarLease/removeCustomer/{id}")
	public void deleteCustomer(@PathVariable Integer id) {
		Customer customer = customerrepo
				.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("Customer data missing/ Not Found"));
		logger.info("Deleting customer!!" +customer.getCustomerId());
		customerrepo.deleteById(id);
	}
	
	/**
	 * Adding a customer
	 * @param customer
	 * @return Http response and header with customer url
	 */

	@PostMapping("/CarLease/addCustomer")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Customer customer){
		ResponseEntity<Object> response = null;
		try {
			Customer savedCustomer = customerrepo.save(customer);
			Customer customerOptional = customerrepo.
					findById(savedCustomer.getCustomerId())
					.orElseThrow(() -> new CustomerNotFoundException("Customer data missing/ Not Found"));
			if(customerOptional.getCar()!=null) 
			{	
				CarDetails car = proxymapper.proxyCalltoRetrieveLeaseCalucationWithCustomerDetails(customerOptional);
				carRepo.save(car);
			}

			URI location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(savedCustomer.getCustomerId())
					.toUri();

			response =  ResponseEntity.created(location).build();
		} catch (CustomerNotFoundException e) {
			logger.info("Customer data missing");
		}
		return response;
	}
	
	/**
	 * Method to update customer car details if not added during customer creation
	 * @param id
	 * @param car
	 * @return Http response and header with customer url
	 */
	@PutMapping("/CarLease/Customers/{id}/addCarDetails")
	public ResponseEntity<Object> addCarDetailsForACoustomer(@PathVariable int id, @RequestBody CarDetails car){
		Customer customerOptional = customerrepo
				.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer data missing/ Not Found"));
		
		CarDetails carDetails = proxymapper.proxyCalltoRetrieveLeaseCalucationWithCarDetails(car);
		logger.info("carData:" +carDetails.getCalculatedLeaseAmount()+" "+carDetails.getDurationofLease());
		carRepo.save(carDetails);
		customerOptional.setCar(carDetails);
		customerrepo.save(customerOptional);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(car.getModel())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
}
