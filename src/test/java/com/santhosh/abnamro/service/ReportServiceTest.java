package com.santhosh.abnamro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.santhosh.abnamro.domain.ClientInfo;
import com.santhosh.abnamro.domain.DailyReport;
import com.santhosh.abnamro.domain.ProductInfo;
import com.santhosh.abnamro.processor.FileInputProcessor;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReportServiceTest {

	@Autowired
    private ReportService reportService;

	@MockBean
	private FileInputProcessor fileInputProcessor;
	
    private List<String> transactionList;
    private List<DailyReport> dailyReportList;
    
    private MockMultipartFile file;
    
    @BeforeEach
    public void setup() throws Exception {
    	transactionList = new ArrayList<>();
    	transactionList.add("315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012380     688032000092500000000             O");
    	transactionList.add("315CL  432100020001SGXDC FUSGX NK    20100910JPY01B 0000000001 0000000000000000000060DUSD000000000030DUSD000000000000DJPY201008200012400     688058000092500000000             O");
    	
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

        DailyReport dailyReport = DailyReport.builder()
                .clientInfo(clientInfo)
                .productInfo(productInfo)
                .totalTransactionAmount(Long.valueOf(2)).build();
        
        dailyReportList = new ArrayList<>();
        dailyReportList.add(dailyReport);
        
        file 
	      = new MockMultipartFile(
	        "file", 
	        "Input.txt", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        "".getBytes()
	      );
    }
    
    
    @Test
    public void processReportSuccess() {
    	Mockito.when(fileInputProcessor.readInput(Mockito.any())).thenReturn(transactionList);
        List<DailyReport> dailyReports = reportService.generateReport(file);
        assertNotNull(dailyReportList);
        assertEquals(dailyReportList.toString(), dailyReports.toString());
    }
    
    @Test
    public void processReportFailure() {
    	Mockito.when(fileInputProcessor.readInput(Mockito.any())).thenReturn(null);
    	Exception exception = assertThrows(RuntimeException.class, () -> {
    		List<DailyReport> dailyReports = reportService.generateReport(file);
    	});
        assertEquals("Cannot invoke \"java.util.List.stream()\" because \"transactions\" is null",exception.getMessage());
    }
    
}
