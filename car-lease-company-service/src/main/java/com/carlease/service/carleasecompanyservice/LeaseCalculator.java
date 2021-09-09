package com.carlease.service.carleasecompanyservice;
/**
 * Utility class 
 * @author Shruthi
 *
 */
public class LeaseCalculator {
	
	
	/**The method is defined to calculate the lease amount using
	 * @param mileage
	 * @param duration
	 * @param interestRate
	 * @param netprice
	 * and
	 * @return calculated lease amount
	 */
	public static double calculateLeaseAmount(long mileage, int duration, double interestRate, double netprice) {
		
		double leaseAmount = (((mileage/12.0) * duration)/netprice)+(((interestRate / 100)*netprice)/12.0);
		
		return leaseAmount;
	}

}
