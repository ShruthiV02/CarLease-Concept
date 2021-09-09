package com.carlease.service.carleaseservice;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.carlease.service.carleaseservice.bean.CarDetails;
import com.carlease.service.carleaseservice.bean.Customer;
import com.carlease.service.carleaseservice.proxy.mapper.CalculationOfLeaseServiceProxyMapper;
import com.carlease.service.carleaseservice.repository.CarRepository;
import com.carlease.service.carleaseservice.repository.CustomerRepository;

@SpringBootTest
public class CarLeaseServiceApplicationTests {
	
	@MockBean
	private CustomerRepository customerrepo;
	
	@MockBean
	private CarRepository carRepo;
	
	@MockBean
	private CalculationOfLeaseServiceProxy proxy;
	
	@MockBean
	private CalculationOfLeaseServiceProxyMapper proxymapper;
	
	@Autowired
	private CarLeaseCustomerController contoller;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testCarLeaseControllerGetAllCustomerMethod() {
		List<Customer> customers1 = buildListCustomerdetails();
		when(customerrepo.findAll()).thenReturn(customers1);
		List<Customer> customers = contoller.getAllCustomers();
		assertNotNull(customers);
	}
	
	@Test
	public void testCarLeaseControllergetCustomerById() {
		Optional<Customer> cust = buildCustomerdetails();
		when(customerrepo.findById(cust.get().getCustomerId())).thenReturn(buildCustomerdetails());
		Customer customer = contoller.getcustomerByid(cust.get().getCustomerId());
		assertNotNull(customer);
		assertEquals(customer.getHouseNumber(), cust.get().getHouseNumber());
		assertEquals(customer.getEmail(), cust.get().getEmail());
	}
	

	@Test
	public void testCarLeaseControllerCreateUser() {
		Optional<Customer> cust = buildCustomerdetails();
		Customer customer = cust.get();
		when(customerrepo.save(customer)).thenReturn(customer);
		when(customerrepo.findById(cust.get().getCustomerId())).thenReturn(buildCustomerdetails());
		when(proxy.retrieveCarDetailsWithLeaseAmount(customer.getCar().getModel(), customer.getCar().getMileage()
				, customer.getCar().getDurationofLease())).thenReturn(customer.getCar());
		when(proxymapper.proxyCalltoRetrieveLeaseCalucationWithCustomerDetails(customer)).thenCallRealMethod();
		ResponseEntity<Object> response = contoller.createUser(customer);
		System.out.println(response.getStatusCode());
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
	}
	
	/**
	 * Used to build mocked data for CarDetails
	 * 
	 * @return CarDetailsFromCompany
	 */
	public CarDetails buildCarDetails() {
		
		CarDetails cardetails = new CarDetails();
		cardetails.setDurationofLease(30);
		cardetails.setGrossPrice(23000);
		cardetails.setMake(2018);
		cardetails.setMileage(23000);
		cardetails.setModel("Hyundai-Bayon");
		cardetails.setNetPrice(20000);
		cardetails.setNoOfDoors(5);
		cardetails.setVersion(2.1f);
		
		return cardetails;
	}
	
	/**
	 * Used to build mock data for customer
	 * @return Customer
	 */
	
	public Optional<Customer> buildCustomerdetails() {
		
		Customer customer = new Customer("Sam","Podium","15A","3211AA","Amersfoort","test1@smail.com","7619816141");
		customer.setCustomerId(1001);
		customer.setCar(buildCarDetails());
		return Optional.of(customer);
	}
	
	/**
	 * Used to build mock data for customer
	 * @return Customer
	 */
	
	public List<Customer> buildListCustomerdetails() {
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setCustomerId(1001);
		customer.setName("Sam");
		customer.setEmail("test1@smail.com");
		customer.setPhone("7619816141");
		customer.setStreet("Podium");
		customer.setHouseNumber("15A");
		customer.setPlace("Amersfoort");
		customer.setZipCode("3211AA");
		customer.setCar(buildCarDetails());
		
		customers.add(customer);
		
		Customer customer1 = new Customer();
		customer.setCustomerId(1002);
		customer.setName("Sunny");
		customer.setEmail("test2@smail.com");
		customer.setPhone("7619816151");
		customer.setStreet("Podium");
		customer.setHouseNumber("16A");
		customer.setPlace("Amersfoort");
		customer.setZipCode("3222AA");
		customer.setCar(buildCarDetails());
		
		customers.add(customer1);
		return customers;
	}

}
