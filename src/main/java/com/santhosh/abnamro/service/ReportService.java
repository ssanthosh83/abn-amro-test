package com.santhosh.abnamro.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.santhosh.abnamro.domain.DailyReport;

public interface ReportService {
	List<DailyReport> generateReport(MultipartFile file) throws Exception;
}
