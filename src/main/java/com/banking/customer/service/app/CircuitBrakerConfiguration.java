package com.banking.customer.service.app;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class CircuitBrakerConfiguration {
	
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> accountServiceCustomizer() {
	  return factory -> {
	    factory.configure(builder -> builder
	      .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
	      .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()), "accountCB");
	  };
	}
	
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> creditServiceCustomizer() {
	  return factory -> {
	    factory.configure(builder -> builder
	      .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
	      .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()), "creditCB");
	  };
	}
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> cardServiceCustomizer() {
	  return factory -> {
	    factory.configure(builder -> builder
	      .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
	      .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()), "cardCB");
	  };
	}
	
}
