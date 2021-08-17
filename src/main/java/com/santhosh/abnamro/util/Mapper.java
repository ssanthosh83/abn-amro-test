package com.santhosh.abnamro.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.santhosh.abnamro.domain.ClientInfo;
import com.santhosh.abnamro.domain.ProductInfo;
import com.santhosh.abnamro.domain.SubTransaction;
import com.santhosh.abnamro.model.Transaction;


/**
 *  This class generates the Transaction object by reading the input string based on their index and the endLength
 *
 */
@Component
public class Mapper {
	
	/**
	 * This method gets the Input string, parses it, builds the Transaction object and returns it.
	 * 
	 * @param String
	 * @return Transaction
	 */
	public Transaction mapTransaction(String s) {
		String recordCode = s.substring(Integer.valueOf(this.recordCode.get("index")), Integer.valueOf(this.recordCode.get("endLength")));
		String clientType = s.substring(Integer.valueOf(this.clientType.get("index")), Integer.valueOf(this.clientType.get("endLength")));
		String clientNumber = s.substring(Integer.valueOf(this.clientNumber.get("index")), Integer.valueOf(this.clientNumber.get("endLength")));
        String accountNumber = s.substring(Integer.valueOf(this.accountNumber.get("index")), Integer.valueOf(this.accountNumber.get("endLength")));
        String subaccountNumber = s.substring(Integer.valueOf(this.subaccountNumber.get("index")), Integer.valueOf(this.subaccountNumber.get("endLength")));
        String oppositePartyCode = s.substring(Integer.valueOf(this.oppositePartyCode.get("index")), Integer.valueOf(this.oppositePartyCode.get("endLength")));
        String productGroupCode = s.substring(Integer.valueOf(this.productGroupCode.get("index")), Integer.valueOf(this.productGroupCode.get("endLength")));
        String exchangeCode = s.substring(Integer.valueOf(this.exchangeCode.get("index")), Integer.valueOf(this.exchangeCode.get("endLength")));
        String symbol = s.substring(Integer.valueOf(this.symbol.get("index")), Integer.valueOf(this.symbol.get("endLength")));
        String expirationDate = s.substring(Integer.valueOf(this.expirationDate.get("index")), Integer.valueOf(this.expirationDate.get("endLength")));
        String currencyCode = s.substring(Integer.valueOf(this.currencyCode.get("index")), Integer.valueOf(this.currencyCode.get("endLength")));
        String movementCode = s.substring(Integer.valueOf(this.movementCode.get("index")), Integer.valueOf(this.movementCode.get("endLength")));
        String buySellCode = s.substring(Integer.valueOf(this.buySellCode.get("index")), Integer.valueOf(this.buySellCode.get("endLength")));
        String quantityLongSign = s.substring(Integer.valueOf(this.quantityLongSign.get("index")), Integer.valueOf(this.quantityLongSign.get("endLength")));
        String quantityLong = s.substring(Integer.valueOf(this.quantityLong.get("index")), Integer.valueOf(this.quantityLong.get("endLength")));
        String quantityShortSign = s.substring(Integer.valueOf(this.quantityShortSign.get("index")), Integer.valueOf(this.quantityShortSign.get("endLength")));
        String quantityShort = s.substring(Integer.valueOf(this.quantityShort.get("index")), Integer.valueOf(this.quantityShort.get("endLength")));
        String exchangeBrokerFee = s.substring(Integer.valueOf(this.exchangeBrokerFee.get("index")), Integer.valueOf(this.exchangeBrokerFee.get("endLength")));
        String exchangeBrokerFeeDC = s.substring(Integer.valueOf(this.exchangeBrokerFeeDC.get("index")), Integer.valueOf(this.exchangeBrokerFeeDC.get("endLength")));
        String exchangeBrokerFeeCurCode = s.substring(Integer.valueOf(this.exchangeBrokerFeeCurCode.get("index")), Integer.valueOf(this.exchangeBrokerFeeCurCode.get("endLength")));
        String clearingFee = s.substring(Integer.valueOf(this.clearingFee.get("index")), Integer.valueOf(this.clearingFee.get("endLength")));
        String clearingFeeDC = s.substring(Integer.valueOf(this.clearingFeeDC.get("index")), Integer.valueOf(this.clearingFeeDC.get("endLength")));
        String clearingFeeCurCode = s.substring(Integer.valueOf(this.clearingFeeCurCode.get("index")), Integer.valueOf(this.clearingFeeCurCode.get("endLength")));
        String commissionFee = s.substring(Integer.valueOf(this.commissionFee.get("index")), Integer.valueOf(this.commissionFee.get("endLength")));
        String commissionFeeDC = s.substring(Integer.valueOf(this.commissionFeeDC.get("index")), Integer.valueOf(this.commissionFeeDC.get("endLength")));
        String commissionFeeCurCode = s.substring(Integer.valueOf(this.commissionFeeCurCode.get("index")), Integer.valueOf(this.commissionFeeCurCode.get("endLength")));
        String transactionDate = s.substring(Integer.valueOf(this.transactionDate.get("index")), Integer.valueOf(this.transactionDate.get("endLength")));
        String futureReference = s.substring(Integer.valueOf(this.futureReference.get("index")), Integer.valueOf(this.futureReference.get("endLength")));
        String ticketNumber = s.substring(Integer.valueOf(this.ticketNumber.get("index")), Integer.valueOf(this.ticketNumber.get("endLength")));
        String externalNumber = s.substring(Integer.valueOf(this.externalNumber.get("index")), Integer.valueOf(this.externalNumber.get("endLength")));
        String transactionPrice = s.substring(Integer.valueOf(this.transactionPrice.get("index")), Integer.valueOf(this.transactionPrice.get("endLength")));
        String traderInitials = s.substring(Integer.valueOf(this.traderInitials.get("index")), Integer.valueOf(this.traderInitials.get("endLength")));
        String oppositeTraderId = s.substring(Integer.valueOf(this.oppositeTraderId.get("index")), Integer.valueOf(this.oppositeTraderId.get("endLength")));
        String openCloseCode = s.substring(Integer.valueOf(this.openCloseCode.get("index")), Integer.valueOf(this.openCloseCode.get("endLength")));
      
        Transaction.TransactionBuilder transactionBuilder = Transaction.builder();
        
        Transaction transaction = transactionBuilder.clientType(clientType)
					 .accountNumber(accountNumber)
					 .subAccountNumber(subaccountNumber)
					 .productGroupCode(productGroupCode)
					 .oppositePartyCode(oppositePartyCode)
					 .clientNumber(clientNumber)
					 .exchangeCode(exchangeCode)
					 .symbol(symbol)
					 .expirationDate(expirationDate)
					 .recordCode(recordCode)
					 .currencyCode(currencyCode)
					 .movementCode(movementCode)
					 .buySellCode(buySellCode)
					 .quantityLongSign(quantityLongSign)
					 .quantityLong(quantityLong)
					 .quantityShortSign(quantityShortSign)
					 .quantityShort(quantityShort)
					 .exchangeBrokerFee(exchangeBrokerFee)
					 .exchangeBrokerFeeDC(exchangeBrokerFeeDC)
					 .exchangeBrokerFeeCurCode(exchangeBrokerFeeCurCode)
					 .clearingFee(clearingFee)
					 .clearingFeeDC(clearingFeeDC)
					 .clearingFeeCurCode(clearingFeeCurCode)
					 .commissionFee(commissionFee)
					 .commissionFeeDC(commissionFeeDC)
					 .commissionFeeCurCode(commissionFeeCurCode)
					 .transactionDate(transactionDate)
					 .futureReference(futureReference)
					 .ticketNumber(ticketNumber)
					 .externalNumber(externalNumber)
					 .transactionPrice(transactionPrice)
					 .traderInitials(traderInitials)
					 .oppositeTraderId(oppositeTraderId)
					 .openCloseCode(openCloseCode)
					 .build();
        
        return transaction;

	}
	
/**
 * This method converts the Transaction into Subtransaction 
 * Subtransaction object is used for generating the Daily Transaction Report
 * 
 * @param transaction
 * @return Subtransaction
 */
public SubTransaction getSubTransaction(Transaction transaction) {
		
		ProductInfo productInfo = ProductInfo.builder()
									.exchangeCode(transaction.getExchangeCode().trim())
									.productGroup(transaction.getProductGroupCode())
									.symbol(transaction.getSymbol().trim())
									.expirationDate(String.valueOf(transaction.getExpirationDate()))
									.build();
		
		ClientInfo clientInfo = ClientInfo.builder()
									.clientType(transaction.getClientType().trim())
									.clientNumber(transaction.getClientNumber())
									.accountNumber(transaction.getAccountNumber())
									.subAccountNumber(transaction.getSubAccountNumber())
									.build();
		
		return SubTransaction.builder()
				.clientInfo(clientInfo)
				.productInfo(productInfo)
				.quantityLong(Long.valueOf(transaction.getQuantityLong()))
				.quantityShort(Long.valueOf(transaction.getQuantityShort()))
				.build();
						
	}

@Value("#{${recordCode}}")
private Map<String, String> recordCode;

@Value("#{${clientType}}")
private Map<String, String> clientType;

@Value("#{${clientNumber}}")
private Map<String, String> clientNumber;

@Value("#{${accountNumber}}")
private Map<String, String> accountNumber;

@Value("#{${subaccountNumber}}")
private Map<String, String> subaccountNumber;

@Value("#{${oppositePartyCode}}")
private Map<String, String> oppositePartyCode;

@Value("#{${productGroupCode}}")
private Map<String, String> productGroupCode;

@Value("#{${exchangeCode}}")
private Map<String, String> exchangeCode;

@Value("#{${symbol}}")
private Map<String, String> symbol;

@Value("#{${expirationDate}}")
private Map<String, String> expirationDate;

@Value("#{${currencyCode}}")
private Map<String, String> currencyCode;

@Value("#{${movementCode}}")
private Map<String, String> movementCode;

@Value("#{${buySellCode}}")
private Map<String, String> buySellCode;

@Value("#{${quantityLongSign}}")
private Map<String, String> quantityLongSign;

@Value("#{${quantityLong}}")
private Map<String, String> quantityLong;

@Value("#{${quantityShortSign}}")
private Map<String, String> quantityShortSign;

@Value("#{${quantityShort}}")
private Map<String, String> quantityShort;

@Value("#{${exchangeBrokerFee}}")
private Map<String, String> exchangeBrokerFee;

@Value("#{${exchangeBrokerFeeDC}}")
private Map<String, String> exchangeBrokerFeeDC;

@Value("#{${exchangeBrokerFeeCurCode}}")
private Map<String, String> exchangeBrokerFeeCurCode;

@Value("#{${clearingFee}}")
private Map<String, String> clearingFee;

@Value("#{${clearingFeeDC}}")
private Map<String, String> clearingFeeDC;

@Value("#{${clearingFeeCurCode}}")
private Map<String, String> clearingFeeCurCode;

@Value("#{${commissionFee}}")
private Map<String, String> commissionFee;

@Value("#{${commissionFeeDC}}")
private Map<String, String> commissionFeeDC;

@Value("#{${commissionFeeCurCode}}")
private Map<String, String> commissionFeeCurCode;

@Value("#{${transactionDate}}")
private Map<String, String> transactionDate;

@Value("#{${futureReference}}")
private Map<String, String> futureReference;

@Value("#{${ticketNumber}}")
private Map<String, String> ticketNumber;

@Value("#{${externalNumber}}")
private Map<String, String> externalNumber;

@Value("#{${transactionPrice}}")
private Map<String, String> transactionPrice;

@Value("#{${traderInitials}}")
private Map<String, String> traderInitials;

@Value("#{${oppositeTraderId}}")
private Map<String, String> oppositeTraderId;

@Value("#{${openCloseCode}}")
private Map<String, String> openCloseCode;


}
