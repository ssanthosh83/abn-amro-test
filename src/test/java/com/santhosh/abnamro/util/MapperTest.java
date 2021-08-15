package com.santhosh.abnamro.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.santhosh.abnamro.domain.ClientInfo;
import com.santhosh.abnamro.domain.ProductInfo;
import com.santhosh.abnamro.domain.SubTransaction;
import com.santhosh.abnamro.model.Transaction;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MapperTest {
	
	@Autowired
    private Mapper mapper;

	private SubTransaction subTransactionExpected;
	private String transactionString;
	
    @BeforeEach
    public void setup(){
    	ClientInfo clientInfo = ClientInfo.builder()
                .clientType("CL")
                .clientNumber("4321")
                .accountNumber("0002")
                .subAccountNumber("0001").build();
		
		ProductInfo productInfo = ProductInfo.builder()
                .exchangeCode("SGX")
                .productGroup("FU")
                .symbol("NK")
                .expirationDate("20100910").build();

        subTransactionExpected = SubTransaction.builder()
                .clientInfo(clientInfo)
                .productInfo(productInfo)
                .quantityLong(Long.valueOf(1))
                .quantityShort(Long.valueOf(0)).build();
        
        transactionString = "315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O";
        

    }
	
	@Test
    public void testSubTransaction() {
        Transaction transaction = mapper.mapTransaction(transactionString);
        SubTransaction subTransactionActual = mapper.getSubTransaction(transaction);
        assertNotNull(subTransactionActual);
        assertEquals(subTransactionExpected.toString(), subTransactionActual.toString());
    }
}
	

