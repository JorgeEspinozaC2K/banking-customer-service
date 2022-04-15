package com.banking.customer.service.app.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Flux<Customer> findByLegalCustomer(Boolean legalCustomer) {
		return customerRepository.findByLegalCustomer(legalCustomer);
	}

	@Override
	public Flux<Customer> findByTributaryIdentifier(int tributaryIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Customer> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Customer> findByPersonalIdentifier(int personalIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Customer> save(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> delete(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
