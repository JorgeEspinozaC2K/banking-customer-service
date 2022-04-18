package com.banking.customer.service.app.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.customer.service.app.enity.DocData;
import com.banking.customer.service.app.model.Customer;
import com.banking.customer.service.app.repository.CustomerRepository;
import com.banking.customer.service.app.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImp implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Flux<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Mono<Object> findById(String id) {
		return customerRepository.findById(id)
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.just(new DocData(id,"404","Customer Not Found"));
					}
					return Mono.just(c);
				});
	}

	@Override
	public Mono<Object> findByPersonalIdentifier(Integer personalIdentifier) {
		
		Mono<Object> dataObject = customerRepository.findByPersonalIdentifier(personalIdentifier)
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.just(new DocData(null,"404","Customer Not Found",personalIdentifier.toString()));
					}
					return Mono.just(c);
				});
		
		return dataObject;
	}

	@Override
	public Mono<Object> save(Customer customer) {
		
		Mono<Object> customerData = customerRepository.findByPersonalIdentifier(customer.getPersonalIdentifier())
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() != null) {
						return Mono.just(new DocData(c.getId(),"302","Customer Already Exist",customer.getPersonalIdentifier().toString()));
					}
					return customerRepository.save(customer);
				});
	
		return customerData;
	}

	@Override
	public Mono<Object> delete(Customer customer) {
		
		Mono<Object> customerData = customerRepository.findById(customer.getId())
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.just(new DocData(c.getId(),"404","Inexisting Customer, can't be eliminated"));
					}
					return customerRepository.delete(customer)
							.then(Mono.just(new DocData(c.getId(),"200","Correct Deletig",c.getPersonalIdentifier().toString())));
				});
		return customerData;
	}

	@Override
	public Mono<Object> findByIdOrPersonalIdentifier(String id, Integer personalIdentifier) {
		Mono<Object> customerData = customerRepository.findByIdOrPersonalIdentifier(id, personalIdentifier)
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() != null) {
						return Mono.just(new DocData(c.getId(),"404","Inexisting Customer, can't be eliminated",personalIdentifier.toString()));
					}
					return Mono.just(c);
				});
		return customerData;
	}

}
