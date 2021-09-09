package com.carlease.service.carleasecompanyservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carlease.service.carleasecompanyservice.bean.CarDetailsFromCompany;

/**
 * JPA repository for car company service
 * used for finding the car details based on model
 * @author Shruthi
 *
 */

public interface CarDetailsFromCompanyRepository extends JpaRepository<CarDetailsFromCompany, Long>{
	
	CarDetailsFromCompany findByModel(String model);
}
