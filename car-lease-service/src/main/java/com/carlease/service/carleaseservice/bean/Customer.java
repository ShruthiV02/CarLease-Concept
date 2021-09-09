package com.carlease.service.carleaseservice.bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/**
 * Customer Entity class containing Customer columns
 * @author Shruthi
 *
 */
@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private int customerId;
	
	@Size(min=2)
	private String name;
	private String street;
	private String houseNumber;
	private String zipCode;
	private String place;
	
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	private String phone;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private CarDetails car;
	
	public Customer() {}
	
	public Customer(String name, String street, String houseNumber, String zipCode, String place, String email,
			String phone) {
		super();
		this.name = name;
		this.street = street;
		this.houseNumber = houseNumber;
		this.zipCode = zipCode;
		this.place = place;
		this.email = email;
		this.phone = phone;		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getHouseNumber() {
		return houseNumber;
	}
	
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public CarDetails getCar() {
		return car;
	}
	
	public void setCar(CarDetails car) {
		this.car = car;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
}
