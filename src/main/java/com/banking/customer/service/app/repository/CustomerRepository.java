package com.banking.customer.service.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.banking.customer.service.app.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
	
	/**
	 * EN: This abstract methods was created because the business necessity, using the
	 the keywords of spring data MongoDB.
	 
	 ES: Estos métodos abstractos fueron creados debido a la necesidad del negocio,
	 usando las palabras clave de spring data MongoDB.
	 */
	
	/**
	 * EN: This method find all the customers depending of they status of legal customer.
	 * 
	 * ES: Este método encuentra a todos los clientes dependiendo de su estatus como
	 * cliente legal.
	 * 
	 * @param legalCustomer Boolean
	 * @return Flux type Customer
	 * */
	public Flux<Customer> findByLegalCustomer(Boolean legalCustomer);
	
	/**
	 * EN: This method find all the customers who belong to a determinate employing entity.
	 * 
	 * ES: Este método encuentra a todos los clientes que pertenecen a una determinada entidad 
	 * empleadora.
	 * 
	 * @param tributaryIdentifier Integer
	 * @return Flux type Customer
	 * */
	public Flux<Customer> findByTributaryIdentifier(int tributaryIdentifier);
	
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
	public Mono<Customer> findByPersonalIdentifier(int personalIdentifier);
}
