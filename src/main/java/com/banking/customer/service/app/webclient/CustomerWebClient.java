package com.banking.customer.service.app.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.banking.customer.service.app.entity.Account;
import com.banking.customer.service.app.entity.Card;
import com.banking.customer.service.app.entity.Credit;
import reactor.core.publisher.Flux;

@Service
public class CustomerWebClient {
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

	public Flux<Account> findCustomerAccounts(String id){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/account/customer/{id}",id)
				.retrieve()
				.bodyToFlux(Account.class)
                .transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("accountCB");
                    return rcb.run(it, throwable -> Flux.empty());
                });
	}
	public Flux<Credit> findCustomerCredits(String id){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/credit/customer/{id}",id)
				.retrieve()
				.bodyToFlux(Credit.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("creditCB");
                    return rcb.run(it, throwable -> Flux.just(Credit.builder().build()));
                });
	}
	public Flux<Card> findCustomerCards(String id){
		return WebClient
				.create("http://localhost:8080")
				.get()
				.uri("/card/customer/{id}",id)
				.retrieve()
				.bodyToFlux(Card.class)
				.transformDeferred(it -> {
                    ReactiveCircuitBreaker rcb = reactiveCircuitBreakerFactory.create("cardCB");
                    return rcb.run(it, throwable -> Flux.just(Card.builder().build()));
                });
	}
}
