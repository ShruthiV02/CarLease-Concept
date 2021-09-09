package com.carlease.service.carleaseservice.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Car Entity class containing carDetails columns
 * @author Shruthi
 *
 */
@Entity
public class CarDetails {
	
	@Id
	@Size(min=3)
	private String model;
	
	@Positive
	private long mileage;
	
	@Positive
	private int durationofLease;
	private int noOfDoors;
	private long make;
	private float version;
	private double grossPrice;
	private double netPrice;
	private double calculatedLeaseAmount;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Customer customer;


	public CarDetails() {}

	public CarDetails(String model, long mileage, int durationofLease, int noOfDoors, long make, float version,
			double grossPrice, double netPrice,double calculatedLeaseAmount ) {
		super();
		this.model = model;
		this.mileage = mileage;
		this.durationofLease = durationofLease;
		this.noOfDoors = noOfDoors;
		this.make = make;
		this.version = version;
		this.grossPrice = grossPrice;
		this.netPrice = netPrice;
		this.calculatedLeaseAmount = calculatedLeaseAmount;
	}
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public long getMileage() {
		return mileage;
	}
	
	public void setMileage(long mileage) {
		this.mileage = mileage;
	}
	
	public int getDurationofLease() {
		return durationofLease;
	}
	
	public void setDurationofLease(int durationofLease) {
		this.durationofLease = durationofLease;
	}
	
	public int getNoOfDoors() {
		return noOfDoors;
	}
	
	public void setNoOfDoors(int noOfDoors) {
		this.noOfDoors = noOfDoors;
	}
	
	public long getMake() {
		return make;
	}
	
	public void setMake(long make) {
		this.make = make;
	}
	public float getVersion() {
		return version;
	}
	
	public void setVersion(float version) {
		this.version = version;
	}
	
	public double getGrossPrice() {
		return grossPrice;
	}
	
	public void setGrossPrice(double grossPrice) {
		this.grossPrice = grossPrice;
	}
	
	public double getNetPrice() {
		return netPrice;
	}
	
	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}
	
	public double getCalculatedLeaseAmount() {
		return calculatedLeaseAmount;
	}
	
	public void setCalculatedLeaseAmount(double calculatedLeaseAmount) {
		this.calculatedLeaseAmount = calculatedLeaseAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
