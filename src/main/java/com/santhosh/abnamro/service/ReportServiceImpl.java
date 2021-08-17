package com.santhosh.abnamro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.santhosh.abnamro.domain.ClientInfo;
import com.santhosh.abnamro.domain.DailyReport;
import com.santhosh.abnamro.domain.ProductInfo;
import com.santhosh.abnamro.domain.SubTransaction;
import com.santhosh.abnamro.model.Transaction;
import com.santhosh.abnamro.processor.FileInputProcessor;
import com.santhosh.abnamro.util.Mapper;

import lombok.extern.slf4j.Slf4j;


/**
 * This class reads the file and returns the Transaction List to be displayed in the UI
 *
 */
@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private FileInputProcessor resInputProcessor;
		
	@Autowired
	private Mapper mapper;
	
	@Autowired
	private KafkaProducer producer;
	
	
	/**
	 * 	This method reads the input file and generates the Daily Transaction report
	 * 
	 *	@param file
	 *	@return List<DailyReport>
	 */
	@Override
	public List<DailyReport> generateReport(MultipartFile file) throws Exception {

		List<String> transactions = resInputProcessor.readInput(file);
		
		Map<ClientInfo, Map<ProductInfo, List<SubTransaction>>> clientMap = transactions.stream()
				.map(mapper::mapTransaction)
				.map(this::sendToProducer)
				.map(mapper::getSubTransaction)
				.collect(Collectors.groupingBy(SubTransaction::getClientInfo, Collectors.groupingBy(SubTransaction::getProductInfo)));

		
		List<DailyReport> dailyReports = new ArrayList<>();
		DailyReport.DailyReportBuilder dailyReportBuilder = DailyReport.builder();
		clientMap.entrySet().stream()
			.forEach(x -> {
				dailyReportBuilder.clientInfo(x.getKey());
				
				Map<ProductInfo, List<SubTransaction>> value = x.getValue();
				value.entrySet().stream()
					.forEach(product -> {
						dailyReportBuilder.productInfo(product.getKey());
						List<SubTransaction> transactionEntryList = product.getValue();
						Long longSum = transactionEntryList.stream().map(t->t.getQuantityLong()).reduce(Long.valueOf(0), Long::sum);
						Long shortSum = transactionEntryList.stream().map(t->t.getQuantityShort()).reduce(Long.valueOf(0), Long::sum);
						dailyReportBuilder.totalTransactionAmount(longSum - shortSum);
						dailyReports.add(dailyReportBuilder.build());
					});
			});
		log.info("DailyReports size : "+dailyReports.size());
		return dailyReports;	
	}
	
	
	/**
	 * This method takes the Transaction object, sends it to the producer and returns the transaction for further processing in the stream
	 * 
	 * @param transaction
	 * @return Transaction
	 */
	private Transaction sendToProducer(Transaction transaction) {
		producer.publish(transaction);
			return transaction;
	}
	

}
