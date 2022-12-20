package com.report.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.report.model.Report;
import com.report.service.ReportService;

@RestController
public class ReportController {
	private ReportService reportService;
	
	public String reportSave(@RequestBody Report report) {
		return reportService.saveReport(report);
	}
	public List<Report> reportShow(){
		return reportService.showReport();
	}
	
}
