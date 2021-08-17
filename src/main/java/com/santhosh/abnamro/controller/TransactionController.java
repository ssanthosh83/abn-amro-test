package com.santhosh.abnamro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.santhosh.abnamro.domain.DailyReport;
import com.santhosh.abnamro.service.ReportService;

import lombok.extern.slf4j.Slf4j;



/**
 * Controller class : Rest APIs for generating daily reports
 *
 */
@Slf4j
@RestController
@RequestMapping("v1")
public class TransactionController {

	@Autowired
	private ReportService reportService;
	
	
	/**
	 * Returns daily summary report of all transactions with Client Info, Product Info and 
	 * total transaction amount
	 * 
	 * @param file
	 * @return 
	 */
	@PostMapping(value="/report")
	public ResponseEntity<List<DailyReport>> getDailySummaryReport(@RequestPart("file") MultipartFile file) {
		
		if(file == null || file.getOriginalFilename() == "") {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		try {
			List<DailyReport> dailyReport = reportService.generateReport(file);
			log.info("The daily transaction report is : ", dailyReport);
			return new ResponseEntity<>(dailyReport, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		
	}
}
