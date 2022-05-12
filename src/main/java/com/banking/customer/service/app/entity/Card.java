package com.banking.customer.service.app.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Card {

	private String id;
	private Boolean debit;
	private String accountId;
	private String customerId;
	private Integer ccv;
	private Double baseCreditLine;
	private Double remainingCreditLine;
	private LocalDate expiration;
	private LocalDate createAt;
	
	
}
