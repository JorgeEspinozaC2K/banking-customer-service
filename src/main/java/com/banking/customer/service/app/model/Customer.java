package com.banking.customer.service.app.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private Integer personalIdentifier;
	
	private Integer tributaryIdentifier;
	
	private Boolean isTributary;
	
	//Email of customer
	//Correo electrónico del cliente
	@Email
	@NotEmpty
	private String email;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	//Date of creation of this customer
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createAt;
	
	//Date of update of this customer
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date updateAt;
	
}
