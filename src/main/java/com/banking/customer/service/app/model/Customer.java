package com.banking.customer.service.app.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

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
	
	//Customer's name
	//Nombre del cliente
	@NotEmpty
	private String name;
	
	//Customer's last name
	//Apellido del cliente
	@NotEmpty
	private String lastName;
	
	//Customer's personal identification number
	//Identificador personal del cliente
	@NotEmpty
	private int personalIdentifier;
	
	//Email of customer
	//Correo electrónico del cliente
	@Email
	@NotEmpty
	private String email;
	
	//This define if the customer is a natural person or a legal person
	//Define si el cliente es una persona natural o una persona jurídica
	private boolean legalCustomer = false;
	
	//In case of legalCustomer was true, this attribute shouldn't be empty
	//En caso de que el atributo legalCustomer fuese verdadero, este atributo no deberia estar vacio
	private int tributaryIdentifier;
	
	//Date of creation of this customer
	private Date createAt = new Date();
	
	//Date of update of this customer
	private Date updateAt;
	
}
