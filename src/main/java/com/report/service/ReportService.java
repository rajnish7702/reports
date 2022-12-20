package com.report.service;

import java.util.List;

import com.report.model.Report;

public interface ReportService {
	public String saveReport(Report report);
	public List<Report> showReport();
}
