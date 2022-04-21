package com.banking.customer.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BankingCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingCustomerServiceApplication.class, args);
	}

}
