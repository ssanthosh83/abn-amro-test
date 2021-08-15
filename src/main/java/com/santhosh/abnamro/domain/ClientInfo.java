package com.santhosh.abnamro.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class ClientInfo {

	private String clientType;
	private String clientNumber;
	private String accountNumber;
	private String subAccountNumber;
	
	@Builder
	public ClientInfo(String clientType, String clientNumber, String accountNumber, String subAccountNumber) {
		super();
		this.clientType = clientType;
		this.clientNumber = clientNumber;
		this.accountNumber = accountNumber;
		this.subAccountNumber = subAccountNumber;
	}
	
}
