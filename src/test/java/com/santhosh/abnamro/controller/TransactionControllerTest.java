package com.santhosh.abnamro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.santhosh.abnamro.domain.ClientInfo;
import com.santhosh.abnamro.domain.DailyReport;
import com.santhosh.abnamro.domain.ProductInfo;
import com.santhosh.abnamro.service.ReportService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TransactionController.class)
class TransactionControllerTest {

	@MockBean
	private ReportService reportService;
	
	@Autowired
	private MockMvc mockMvc;
	 
	private List<DailyReport> dailyReportList;
	
	private MockMultipartFile file;
    
	@BeforeEach
	public void setup() throws Exception {
		dailyReportList = new ArrayList<>();
		DailyReport dailyReport = DailyReport.builder()
									.clientInfo(ClientInfo.builder()
											.clientType("CL")
											.clientNumber("1234")
											.accountNumber("0003")
											.subAccountNumber("0001")
											.build())
									.productInfo(ProductInfo.builder()
											.productGroup("FU")
											.expirationDate("20100910")
											.symbol("N1")
											.exchangeCode("CME")
											.build())
									.totalTransactionAmount(Long.valueOf(285))
									.build();
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
	public void testReportSuccess() throws Exception {
		Mockito.when(reportService.generateReport(Mockito.any())).thenReturn(dailyReportList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/v1/report").file(file);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "[{\"clientInfo\":{\"clientType\":\"CL\",\"clientNumber\":\"1234\",\"accountNumber\":\"0003\",\"subAccountNumber\":\"0001\"},\"productInfo\":{\"exchangeCode\":\"CME\",\"productGroup\":\"FU\",\"symbol\":\"N1\",\"expirationDate\":\"20100910\"},\"totalTransactionAmount\":285}]";
		assertEquals(result.getResponse().getContentAsString(), expected);
	}
	
	@Test
	public void testReportException() throws Exception {
		Mockito.when(reportService.generateReport(Mockito.any())).thenThrow(new RuntimeException("Exception occurred!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.multipart("/v1/report").file(file);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals("500 INTERNAL_SERVER_ERROR \"Exception occurred!\"; nested exception is java.lang.RuntimeException: Exception occurred!",result.getResolvedException().getMessage());
	}
	
	
}
