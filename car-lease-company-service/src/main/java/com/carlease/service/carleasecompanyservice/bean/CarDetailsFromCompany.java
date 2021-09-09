package com.carlease.service.carleasecompanyservice.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


/**
 * Entity Class for Car Details maintained by company
 * @id Model
 * The class has no arg and also arguement constructors with 
 * getter and setters for all the columns
 * @author Shruthi
 *
 */
@Entity
public class CarDetailsFromCompany {
	
	@Id
	@Size(min=3)
	private String model;
	
	@Column(name = "num_of_doors")
	private int noOfDoors;
	private long make;
	private float version;
	
	@Column(name = "gross_price")
	private double grossPrice;
	
	@Column(name = "net_price")
	private double netPrice;
	
	@Column(name = "interest_rate")
	private double interestRate;
	
	private double calculatedLeaseAmount;
	
	@Positive
	private long mileage;
	
	@Positive
	private int durationofLease;
	
	public CarDetailsFromCompany() {}

	public CarDetailsFromCompany(String model, int noOfDoors, long make, float version, double grossPrice,
			double netPrice, double interestRate) {
		super();
		this.model = model;
		this.noOfDoors = noOfDoors;
		this.make = make;
		this.version = version;
		this.grossPrice = grossPrice;
		this.netPrice = netPrice;
		this.interestRate = interestRate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public double getCalculatedLeaseAmount() {
		return calculatedLeaseAmount;
	}
	
	public void setCalculatedLeaseAmount(double calculatedLeaseAmount) {
		this.calculatedLeaseAmount = calculatedLeaseAmount;
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

}
