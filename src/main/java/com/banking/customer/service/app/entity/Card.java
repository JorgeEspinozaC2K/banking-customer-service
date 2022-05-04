package com.banking.customer.service.app.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Card {

	private String id;
	private Boolean debit = false;
	private String accountId;
	private String customerId;
	private Integer ccv;
	private Double baseCreditLine;
	private Double remainingCreditLine;
	private LocalDate expiration;
	private LocalDate createAt;
	
	
}
