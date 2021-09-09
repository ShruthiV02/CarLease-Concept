package com.carlease.service.eurekanamingservercarlease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaNamingServerCarleaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaNamingServerCarleaseApplication.class, args);
	}

}
