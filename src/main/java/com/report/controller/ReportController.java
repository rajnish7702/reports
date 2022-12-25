package com.report.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.report.model.CitizenPlan;
import com.report.model.SearchRequest;
import com.report.service.ReportService;

@RestController
public class ReportController {
	
	
	@Autowired
	public ReportService repoService;
	
	@GetMapping("/planname")
	public ResponseEntity<List<String>> getPlainName() {
		List<String> names = repoService.getPlanNames();
		return new ResponseEntity<>(names,HttpStatus.OK);
	}
	@GetMapping("/plainstatus")
	public ResponseEntity<List<String>> getPlainStatuses(){
		List<String> status = repoService.getPlanStatus();
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<CitizenPlan>> serch(@RequestBody SearchRequest request){
		List<CitizenPlan> searchReq = repoService.getCitizenPlans(request);
		return new ResponseEntity<>(searchReq,HttpStatus.OK);
	}
	@GetMapping("/excle")
	public void generateExceReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String headerKay = "Content-Disposition";
		String headerValue = "attachement;filename=report.xls";
		response.setHeader(headerKay, headerValue);
		repoService.exportExcel(response);
		response.flushBuffer();
	}
	@GetMapping("/pdf")
	public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=citizenPlain" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);
		repoService.exportPdf(response);
		response.flushBuffer();
	}
}
