package com.banking.customer.service.app;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.banking.customer.service.app.model.Customer;
import com.banking.customer.service.app.repository.CustomerRepository;
import com.banking.customer.service.app.service.CustomerService;

import reactor.core.publisher.Flux;

@SpringBootTest(classes = {CustomerServiceTest.class})
public class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepo;
	
	@InjectMocks
	CustomerService customerService;
	
	public Flux<Customer> customers;
	
	@Test
	@Order(1)
	public void test_findAll() {
		Flux<Customer> customers =  Flux.just(new Customer());
		when(customerRepo.findAll()).thenReturn(null);
		customerService.findAll();
	}
	
}
