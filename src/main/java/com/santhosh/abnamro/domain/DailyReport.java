package com.santhosh.abnamro.domain;

import lombok.Builder;
import lombok.Data;


/**
 * This class defines the structure of Daily Transaction Report
 *
 */
@Data
public class DailyReport {

	private ClientInfo clientInfo;
	private ProductInfo productInfo;
	private Long totalTransactionAmount;
	
	@Builder
	public DailyReport(ClientInfo clientInfo, ProductInfo productInfo, Long totalTransactionAmount) {
		super();
		this.clientInfo = clientInfo;
		this.productInfo = productInfo;
		this.totalTransactionAmount = totalTransactionAmount;
	}
	
}
