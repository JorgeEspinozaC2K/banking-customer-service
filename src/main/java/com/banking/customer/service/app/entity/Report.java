package com.banking.customer.service.app.entity;


import com.banking.customer.service.app.model.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
	private Customer customer;
	private Products products;
}
