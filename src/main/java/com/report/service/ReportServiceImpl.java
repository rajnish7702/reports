package com.report.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.report.model.Report;
import com.report.repository.ReportRepsitory;
@Service
public class ReportServiceImpl implements ReportService {

	private ReportRepsitory reportRepo;
	@Override
	public String saveReport(Report report) {
		reportRepo.save(report);
		if(report.getReportId()!=null) {
			return "Record submited sucessfully";
		}
		else {
			return "Record submited feild";
		}
	}

	@Override
	public List<Report> showReport() {
		return reportRepo.findAll();
	}

}
