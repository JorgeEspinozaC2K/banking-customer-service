package com.banking.customer.service.app.service;

import com.banking.customer.service.app.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
	
	public Flux<Customer> findAll();
	public Flux<Customer> findByLegalCustomer(Boolean legalCustomer);
	public Flux<Customer> findByTributaryIdentifier(int tributaryIdentifier);
	
	public Mono<Customer> findById(String id);
	public Mono<Customer> findByPersonalIdentifier(int personalIdentifier);
	public Mono<Customer> save(Customer customer);
	
	public Mono<Void> delete(Customer customer);
}
