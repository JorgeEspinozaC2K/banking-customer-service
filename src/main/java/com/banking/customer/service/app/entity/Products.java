package com.banking.customer.service.app.entity;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {
	
	private List<Account> accounts;
	private List<Credit> credits;
	private List<Card> cards;

}
