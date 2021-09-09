package com.carlease.service.carleaseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlease.service.carleaseservice.bean.Customer;

/*JPA Repository for Customer Entity*/
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
