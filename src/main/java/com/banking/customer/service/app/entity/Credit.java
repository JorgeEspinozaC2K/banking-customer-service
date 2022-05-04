package com.banking.customer.service.app.entity;

import java.util.Date;

import lombok.Data;


@Data
public class Credit {
	
	private String id;
	
	private Boolean forCard;
	
	private Long cardNumber;
	
	private String customerId;
	
	private Boolean fullyPaid;
	
	private Double totalLoan;
	
	private Double totalPaid;
	
	private Double remainingLoan;
	
	private Boolean activeLoan;
	
	private Boolean claimedLoan;
	
	private Double interestRate;
	
	private Double interest;
	
	private Double met;
	
	private Double crf;
	
	private Integer actualQuota;
	
	private Integer totalQuotas;
	
	private Integer remainingQuotas;
	
	private Double nextQuotaAmount;
    
	private Double nextMinPaymentAmount;
	
	private Date quotaNextPaymentDate;
	
	private Date quotaLastPaymentDate;
	
	private Date fullyPaymentDate;
	
	private Date updateAt;
	
	private Date createAt;
}
