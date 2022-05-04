package com.banking.customer.service.app.entity;

import java.util.Date;

import lombok.Data;


@Data
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
	
	private Date expirationDay = null;
	
	private Date createAt;
}
