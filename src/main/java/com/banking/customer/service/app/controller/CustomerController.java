package com.banking.customer.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.customer.service.app.model.Customer;
import com.banking.customer.service.app.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public Flux<Customer> index(){
		return customerService.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Object> findById(@PathVariable String id){
		return customerService.findById(id);
	}
	
	@GetMapping("/pid/{pid}")
	public Mono<Object> findBypersonalIdentifier(@PathVariable Integer pid){
		return customerService.findByPersonalIdentifier(pid);
	}
	
	@GetMapping("/ipid/{ipid}")
	public Mono<Object> findByIdOrpersonalIdentifier(@PathVariable String ipid){
		return customerService.findByIdOrPersonalIdentifier(ipid);
	}
	
	@PostMapping("/new")
	public Mono<Object> newCustomer(@RequestBody Customer customer){
		return customerService.save(customer);
	}
	
	@DeleteMapping("/delete")
	public Mono<Object> deleteCustomer(@RequestBody Customer customer){
		return customerService.delete(customer);
	}

}
