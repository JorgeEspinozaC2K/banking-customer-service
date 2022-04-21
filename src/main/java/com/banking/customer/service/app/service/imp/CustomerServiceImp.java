package com.banking.customer.service.app.service.imp;


import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.customer.service.app.model.Customer;
import com.banking.customer.service.app.repository.CustomerRepository;
import com.banking.customer.service.app.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImp implements CustomerService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImp.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Flux<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Mono<Customer> findById(String id) {
		return customerRepository.findById(id)
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.error(new InterruptedException("Customer not found"));
					}
					return Mono.just(c);
				}).onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Customer> findByPersonalIdentifier(Integer personalIdentifier) {
		
		return customerRepository.findByPersonalIdentifier(personalIdentifier)
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.error(new InterruptedException("Customer not found"));
					}
					return Mono.just(c);
				}).onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Customer> save(Customer customer) {
		
		LocalDate date = customer.getBirthDate().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		Period edad = Period.between(date, LocalDate.now());
		
		if(edad.getYears() < 18) {
			return Mono.error(new InterruptedException("The client cant be a minor"));
		} 
		else {
			if(customer.getId()==null) {
				return customerRepository.findByPersonalIdentifier(customer.getPersonalIdentifier())
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getPersonalIdentifier() != null) {
						return Mono.error(new InterruptedException("Customer already exist"));
					}
					customer.setCreateAt(new Date());
					return customerRepository.save(customer);
				}).onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});	
			}else {
				return customerRepository.findById(customer.getId())
						.defaultIfEmpty(new Customer())
						.flatMap(c -> {
							if (c.getId() == null) {
								return Mono.error(new InterruptedException("404 Customer Not Found, can't update ID:" + customer.getPersonalIdentifier().toString()));
							}
							customer.setPersonalIdentifier(c.getPersonalIdentifier());
							customer.setCreateAt(c.getCreateAt());
							return customerRepository.save(customer);
						}).onErrorResume(_ex -> {
							log.error(_ex.getMessage());
							return Mono.empty();
						});
			}
			
			
		}
	}

	@Override
	public Mono<Void> delete(Customer customer) {
		
		return customerRepository.findById(customer.getId())
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.error(new InterruptedException("404 Inexisting Customer, can't be eliminated"));
					}
					return customerRepository.delete(customer);
				}).onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Customer> findByIdOrPersonalIdentifier(String id) {
		Integer pid = id.matches("-?\\d+") ? Integer.parseInt(id) : 0;
		
		return customerRepository.findByIdOrPersonalIdentifier(id,pid)
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.error(new InterruptedException("404 Inexisting Customer WITH PID :" + pid.toString()));
					}
					return Mono.just(c);
				}).onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

}
