package com.carlease.service.carleaseservice;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.carlease.service.carleaseservice.bean.UserCredentials;
import com.carlease.service.carleaseservice.repository.UserRepository;


@SpringBootApplication
@EnableFeignClients("com.carlease.service.carleaseservice")
@EnableDiscoveryClient
public class CarLeaseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarLeaseServiceApplication.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepository userRepository) {
		return (ApplicationArguments args) ->  dataSetup(userRepository);
	}

	private void dataSetup(UserRepository userRepository) {
		UserCredentials test = new UserCredentials("test@abc.com","$2a$12$VrGHIfAvfCRpEgTiUcrPu.PXBBpLQo2trRQPjfu9MUBuW5mDovy3C","ROLE_USER");
		userRepository.save(test);
		
	}

}
