package com.banking.customer.service.app.service.imp;


import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

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
		
		return customerRepository.findByPersonalIdentifier(personalIdentifier)
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.just(new DocData(null,"404","Customer Not Found",personalIdentifier.toString()));
					}
					return Mono.just(c);
				});
	}

	@Override
	public Mono<Object> save(Customer customer) {
		
		LocalDate date = customer.getBirthDate().toInstant()
			      .atZone(ZoneId.systemDefault())
			      .toLocalDate();
		
		Period edad = Period.between(date, LocalDate.now());
		
		if(edad.getYears() < 18) {
			return Mono.just(new DocData(null,"406","Customer can't be a minor",customer.getPersonalIdentifier().toString()));
		} 
		else {
			if(customer.getId()==null) {
				return customerRepository.findByPersonalIdentifier(customer.getPersonalIdentifier())
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getPersonalIdentifier() != null) {
						return Mono.just(new DocData(c.getId(),"302","Customer Already Exist",customer.getPersonalIdentifier().toString()));
					}
					customer.setCreateAt(new Date());
					return customerRepository.save(customer);
				});	
			}else {
				return customerRepository.findById(customer.getId())
						.defaultIfEmpty(new Customer())
						.flatMap(c -> {
							if (c.getId() == null) {
								return Mono.just(new DocData(c.getId(),"404","Customer Not Found, can't update",customer.getPersonalIdentifier().toString()));
							}
							customer.setPersonalIdentifier(c.getPersonalIdentifier());
							customer.setCreateAt(c.getCreateAt());
							return customerRepository.save(customer);
						});
			}
			
			
		}
	}

	@Override
	public Mono<Object> delete(Customer customer) {
		
		return customerRepository.findById(customer.getId())
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.just(new DocData(c.getId(),"404","Inexisting Customer, can't be eliminated"));
					}
					return customerRepository.delete(customer)
							.then(Mono.just(new DocData(c.getId(),"200","Correct Deletig",c.getPersonalIdentifier().toString())));
				});
	}

	@Override
	public Mono<Object> findByIdOrPersonalIdentifier(String id) {
		Integer pid = id.matches("-?\\d+") ? Integer.parseInt(id) : 0;
		
		return customerRepository.findByIdOrPersonalIdentifier(id,pid)
				.defaultIfEmpty(new Customer())
				.flatMap(c -> {
					if (c.getId() == null) {
						return Mono.just(new DocData(c.getId(),"404","Inexisting Customer",pid.toString()));
					}
					return Mono.just(c);
				});
	}

}
