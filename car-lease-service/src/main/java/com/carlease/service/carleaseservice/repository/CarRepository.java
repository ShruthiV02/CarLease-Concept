package com.carlease.service.carleaseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlease.service.carleaseservice.bean.CarDetails;

/*JPA Repository for Car Entity*/
@Repository
public interface CarRepository extends JpaRepository<CarDetails, Long>{

}
