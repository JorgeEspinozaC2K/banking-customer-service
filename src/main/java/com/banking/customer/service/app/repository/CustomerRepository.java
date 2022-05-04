package com.banking.customer.service.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.banking.customer.service.app.model.Customer;

import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
	
	/**
	 * EN: This abstract methods was created because the business necessity, using the
	 the keywords of spring data MongoDB.
	 
	 ES: Estos métodos abstractos fueron creados debido a la necesidad del negocio,
	 usando las palabras clave de spring data MongoDB.
	 */
	
	/**
	 * EN: This method find the specific customer depending of his personal identifier number
	 * also known as identity document.
	 * 
	 * ES: Este método encuentra el cliente específico dependiendo de su numero de identificador
	 * personal conicido tambien como documento de identidad.
	 * 
	 * @param personalIdentifier Integer
	 * @return Mono type Customer
	 * */
	public Mono<Customer> findByPersonalIdentifier(String personalIdentifier);
	
	public Mono<Customer> findByTributaryIdentifier(String tributaryIdentifier);
	
	public Mono<Customer> findByIdOrPersonalIdentifier(String id,String personalIdentifier);
}
