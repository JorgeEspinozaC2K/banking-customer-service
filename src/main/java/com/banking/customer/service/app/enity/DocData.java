package com.banking.customer.service.app.enity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DocData {
	
	private String service = "Customer Service";
	
	private String personalId;
	
	private String itemId;
	
	private String errorCode;
	
	private String errorMessage;

	public DocData(String itemId, String errorCode, String errorMessage) {
		this.itemId = itemId;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public DocData(String itemId, String errorCode, String errorMessage ,String personalId) {
		this(itemId,errorCode,errorMessage);
		this.personalId = personalId;
	}
	
	
	
}
