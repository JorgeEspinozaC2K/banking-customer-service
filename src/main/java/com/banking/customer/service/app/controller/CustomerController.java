package com.banking.customer.service.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.customer.service.app.entity.Report;
import com.banking.customer.service.app.model.Customer;
import com.banking.customer.service.app.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public Flux<Customer> index(){
		return customerService.findAll();
	}
	
	@GetMapping("/{id}")
	public Mono<Customer> findById(@PathVariable String id){
		return customerService.findById(id);
	}
	@GetMapping("/report/{id}")
	public Mono<Report> reportOfCustomer(@PathVariable String id){
		return customerService.customerReport(id);
	}
	
	@GetMapping("/pid/{pid}")
	public Mono<Customer> findBypersonalIdentifier(@PathVariable String pid){
		return customerService.findByPersonalIdentifier(pid);
	}
	
	@GetMapping("/trib/{tid}")
	public Mono<Customer> findByTributaryIdentifier(@PathVariable String tid){
		return customerService.findByTributaryIdentifier(tid);
	}
	
	@GetMapping("/ipid/{ipid}")
	public Mono<Customer> findByIdOrpersonalIdentifier(@PathVariable String ipid){
		return customerService.findByIdentifiers(ipid);
	}
	
	@PostMapping("/new")
	public Mono<Customer> newCustomer(@RequestBody Customer customer){
		return customerService.save(customer);
	}
	
	@DeleteMapping("/delete")
	public Mono<Void> deleteCustomer(@RequestBody Customer customer){
		return customerService.delete(customer);
	}

}
