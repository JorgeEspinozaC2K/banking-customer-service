package com.banking.customer.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.customer.service.app.model.Customer;
import com.banking.customer.service.app.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public Flux<Customer> index(){
		return customerService.findAll();
	}

}
