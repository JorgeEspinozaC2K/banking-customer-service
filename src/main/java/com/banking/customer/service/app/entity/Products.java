package com.banking.customer.service.app.entity;


import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Products {
	
	private List<Account> accounts;
	private List<Credit> credits;
	private List<Card> cards;

}
