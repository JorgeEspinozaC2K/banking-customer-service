package com.banking.customer.service.app.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.banking.customer.service.app.entity.Account;
import com.banking.customer.service.app.entity.Card;
import com.banking.customer.service.app.entity.Credit;

import reactor.core.publisher.Flux;

public class CustomerWebClient {
private Builder customerWebClient = WebClient.builder();
	
	public Flux<Account> findCustomerAccounts(String id){
		return customerWebClient.build()
				.get()
				.uri("http://localhost:8080/account/customer/{id}",id)
				.retrieve()
				.bodyToFlux(Account.class);
	}
	public Flux<Credit> findCustomerCredits(String id){
		return customerWebClient.build()
				.get()
				.uri("http://localhost:8080/credit/customer/{id}",id)
				.retrieve()
				.bodyToFlux(Credit.class);
	}
	public Flux<Card> findCustomerCards(String id){
		return customerWebClient.build()
				.get()
				.uri("http://localhost:8080/card/customer/{id}",id)
				.retrieve()
				.bodyToFlux(Card.class);
	}
}
