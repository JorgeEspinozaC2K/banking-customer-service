package com.banking.customer.service.app.service;

import com.banking.customer.service.app.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
	
	//All the methods that returns an flux type containing the same data type of the ruling class.
	//Todos los métodos que devuelven un tipo flux que conteniendo el mismo tipo de dato que la clase dominante.
	
	public Flux<Customer> findAll();
	
	
	//All the methods that returns a mono type containing the same data type of the ruling class.
	//Todos los métodos que devuelven un tipo de dato mono conteniendo el mismo tipo de dato dominante
	
	public Mono<Object> findById(String id);
	public Mono<Object> findByPersonalIdentifier(Integer personalIdentifier);
	public Mono<Object> findByIdOrPersonalIdentifier(String id,Integer personalIdentifier);
	public Mono<Object> save(Customer customer);
	
	
	//All the methods that returns an mono type containing a different data type than the ruling class.
	//Todos los métodos que devuelven mono con un contenido diferente al de la clase dominante.
	
	public Mono<Object> delete(Customer customer);
}
