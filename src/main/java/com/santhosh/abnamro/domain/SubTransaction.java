package com.santhosh.abnamro.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class SubTransaction {

	private ClientInfo clientInfo;
	private ProductInfo productInfo;
	private Long quantityLong;
	private Long quantityShort;
	
	@Builder
	public SubTransaction(ClientInfo clientInfo, ProductInfo productInfo, Long quantityLong,
			Long quantityShort) {
		super();
		this.clientInfo = clientInfo;
		this.productInfo = productInfo;
		this.quantityLong = quantityLong;
		this.quantityShort = quantityShort;
	}
	
}
