package com.carlease.service.carleasecompanyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CarLeaseCompanyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarLeaseCompanyServiceApplication.class, args);
	}

}
