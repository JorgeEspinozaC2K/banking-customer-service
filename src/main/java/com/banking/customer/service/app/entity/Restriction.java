package com.banking.customer.service.app.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Restriction {

	private String id;
	
	private String name;
	
	private Integer knownAs;
	
	private Boolean offer;
	
	private Double maintCommission;
	
	private Boolean hasMaxMove;
	
	private Integer maxMovements;
	
	private Boolean hasSpecificDay;
	
	private Boolean haveExpirationDate;
	
	private Date expirationDay;
	
	private Date createAt;
}
