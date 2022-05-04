package com.banking.customer.service.app.entity;

import java.util.Date;
import java.util.List;

import com.banking.customer.service.app.model.Customer;


import lombok.Data;


@Data
public class Account {
	
	private String id;

	private Long accountNumber;
	
	private List<Customer> owners;
	private List<Customer> authorities;
	
	private Integer productType;
	private Restriction restrictions;
	private Boolean offer = false;

	private Integer monthMoves;
 
	private Date witdrawalDay;

	private Date depositDay;

	private Double amountLeft;

	private Date modify;

	private Date createAt;
}
