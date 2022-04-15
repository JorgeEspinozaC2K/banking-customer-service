package com.banking.customer.service.app.model;

import java.util.Date;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {
	
	@Id
	private String id;
	//Customer name
	private String name;
	//Customer last name
	private String lastName;
	//Customer's personal identification number
	private int personalIdentifier;
	//Email of customer
	@Email
	private String email;
	//This define if the customer is a natural person or a legal person
	private boolean legalCustomer;
	//In case of legal customer was true, this attribute shouldn't be empty
	private int tributaryIdentifier;
	//Date of creation of this customer
	private Date createAt;
	//Date of update of this customer
	private Date updateAt;
	
}
