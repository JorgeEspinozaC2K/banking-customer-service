package com.banking.customer.service.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.banking.customer.service.app.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
	
	/**
	 * This abstract methods was created because the business necessity using the
	 the keywords of spring data mongoDB
	 */
	
	/**
	 * This method find all the customers depending of they status of legal customer
	 * @param legalCustomer Boolean
	 * @return Flux type Customer
	 * */
	public Flux<Customer> findByLegalCustomer(Boolean legalCustomer);
	
	/**This method find all the customers who belong to a determinate employing entity
	 * 
	 * @param tributaryIdentifier Integer
	 * @return Flux type Customer
	 * */
	public Flux<Customer> findByTributaryIdentifier(int tributaryIdentifier);
	
	/**This method find the specific customer depending of his personal identifier number
	 * 
	 * @param personalIdentifier Integer
	 * @return Mono type Customer
	 * */
	public Mono<Customer> findByPersonalIdentifier(int personalIdentifier);
}
