package com.banking.customer.service.app.service.imp;

import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.banking.customer.service.app.entity.Account;
import com.banking.customer.service.app.entity.Card;
import com.banking.customer.service.app.entity.Credit;
import com.banking.customer.service.app.entity.Products;
import com.banking.customer.service.app.entity.Report;
import com.banking.customer.service.app.model.Customer;
import com.banking.customer.service.app.repository.CustomerRepository;
import com.banking.customer.service.app.service.CustomerService;
import com.banking.customer.service.app.webclient.CustomerWebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImp implements CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImp.class);

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerWebClient customerWebClient;

	@Override
	public Flux<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Mono<Customer> findById(String id) {
		return customerRepository.findById(id).defaultIfEmpty(new Customer()).flatMap(
				c -> c.getId() == null ? Mono.error(new InterruptedException("Customer not found")) : Mono.just(c))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Customer> findByPersonalIdentifier(String personalIdentifier) {

		return customerRepository.findByPersonalIdentifier(personalIdentifier).defaultIfEmpty(new Customer()).flatMap(
				c -> c.getId() == null ? Mono.error(new InterruptedException("Customer not found")) : Mono.just(c))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Customer> save(Customer customer) {

		Integer edad = Period.between(customer.getBirthDate(), LocalDate.now()).getYears();
		
		
		if (customer.getId() == null) {
				
			if (customer.getIsTributary()) {
				customer.setPersonalIdentifier(null);
				if (customer.getTributaryIdentifier() == null) {
					return Mono.error(new InterruptedException("Tributary identifier cannot be empty if is tributary"));
				}

			}else {
				customer.setTributaryIdentifier(null);
			}
				
				return customerRepository.findByPersonalIdentifier(customer.getPersonalIdentifier())
						.defaultIfEmpty(new Customer())
						.flatMap(c ->{
							if (edad < 18) {
								return Mono.error(new InterruptedException("The client cant be a minor"));
							}
							if(c.getId() != null) {
								return Mono.error(new InterruptedException("Customer already exist"));
							}
							return customerRepository.save(customer);
						})
						.doOnError(_ex->log.error(_ex.getMessage()))
						.onErrorResume(_ex -> Mono.empty());
		}
		return customerRepository.findById(customer.getId())
				.switchIfEmpty(Mono.error(new InterruptedException("404 Customer Not Found, can't update ID:"
								+ customer.getPersonalIdentifier().toString())))
				.map(c -> {
					customer.setTributaryIdentifier(c.getTributaryIdentifier());
					customer.setPersonalIdentifier(c.getPersonalIdentifier());
					customer.setCreateAt(c.getCreateAt());
					return customer;
				}).flatMap(c-> customerRepository.save(customer))
				.doOnError(_ex->log.error(_ex.getMessage()))
				.onErrorResume(_ex -> Mono.empty());
	}

	@Override
	public Mono<Void> delete(Customer customer) {

		return customerRepository.findById(customer.getId()).defaultIfEmpty(new Customer())
				.flatMap(c -> c.getId() == null
						? Mono.error(new InterruptedException("404 Inexisting Customer, can't be eliminated"))
						: customerRepository.delete(customer))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Customer> findByIdentifiers(String id) {
		String pid = id;
		return customerRepository.findByPersonalIdentifierOrTributaryIdentifier(id, pid)
				.defaultIfEmpty(new Customer())
				.flatMap(c -> c.getId() == null
						? Mono.error(new InterruptedException("404 Inexisting Customer WITH PID :" + pid.toString()))
						: Mono.just(c))
				.onErrorResume(_ex -> {
					log.error(_ex.getMessage());
					return Mono.empty();
				});
	}

	@Override
	public Mono<Report> customerReport(String id) {
		return findByIdentifiers(id)
				.defaultIfEmpty(new Customer())
				.flatMap(customer->{
					return customerWebClient.findCustomerAccounts(id)
							.defaultIfEmpty(new Account())
							.collectList()
							.flatMap(accounts->{
								return customerWebClient.findCustomerCredits(id)
										.defaultIfEmpty(new Credit())
										.collectList()
										.flatMap(credits->{
											return customerWebClient.findCustomerCards(id)
											.defaultIfEmpty(new Card())
											.collectList()
											.flatMap(cards->{
												Products prod = new Products();
												Report rep = new Report();
												prod.setAccounts(accounts);
												prod.setCredits(credits);
												prod.setCards(cards);
												rep.setCustomer(customer);
												rep.setProducts(prod);
												return Mono.just(rep);
											});
										});
							});
				});
	}

	@Override
	public Mono<Customer> findByTributaryIdentifier(String tributaryIdentifier) {
		return customerRepository.findByTributaryIdentifier(tributaryIdentifier)
				.switchIfEmpty(Mono.error(new InterruptedException("Tributary customer not found")))
				.flatMap(c -> Mono.just(c))
				.doOnError(error -> log.error(error.getMessage()))
				.onErrorResume(error -> Mono.empty());
	}

}
