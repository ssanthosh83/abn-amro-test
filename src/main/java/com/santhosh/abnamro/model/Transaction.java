package com.santhosh.abnamro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String recordCode;
	private String clientType;
	private String clientNumber;
	private String accountNumber;
	private String subAccountNumber;
	private String oppositePartyCode;
	private String productGroupCode;
	private String exchangeCode;
	private String symbol;
	private String expirationDate;
	private String currencyCode;
	private String movementCode;
	private String buySellCode;
	private String quantityLongSign;
	private String quantityLong;
	private String quantityShortSign;
	private String quantityShort;
	private String exchangeBrokerFee;
	private String exchangeBrokerFeeDC;
	private String exchangeBrokerFeeCurCode;
	private String clearingFee;
	private String clearingFeeDC;
	private String clearingFeeCurCode;
	private String commissionFee;
	private String commissionFeeDC;
	private String commissionFeeCurCode;
	private String transactionDate;
	private String futureReference;
	private String ticketNumber;
	private String externalNumber;
	private String transactionPrice;
	private String traderInitials;
	private String oppositeTraderId;
	private String openCloseCode;
}
