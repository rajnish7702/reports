package com.report.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.report.model.CitizenPlan;
import com.report.model.SearchRequest;
import com.report.repository.ReportRepsitory;


@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportRepsitory reportRepo;

	@Override
	public List<String> getPlanNames() {
		return reportRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		return reportRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> getCitizenPlans(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		if (request.getPlanName() != null && !request.getPlanName().equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if (request.getPlanStatus() != null && !request.getPlanStatus().equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		Example<CitizenPlan> example = Example.of(entity);
		List<CitizenPlan> record = reportRepo.findAll();
		return record;
	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {
		List<CitizenPlan> reports = reportRepo.findAll();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet();
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Id");
		headerRow.createCell(1).setCellValue("Plain Name");
		headerRow.createCell(2).setCellValue("Plain Staus");
		headerRow.createCell(3).setCellValue("Citizen Name");
		headerRow.createCell(4).setCellValue("Citizen Email");
		headerRow.createCell(5).setCellValue("Gender");
		headerRow.createCell(6).setCellValue("Phone");
		headerRow.createCell(7).setCellValue("SSN");
		int DataRowInfo = 1;
		for (CitizenPlan citizenPlan : reports) {
			XSSFRow dataRow = sheet.createRow(DataRowInfo);
			dataRow.createCell(0).setCellValue(citizenPlan.getCId());
			dataRow.createCell(1).setCellValue(citizenPlan.getPlanName());
			dataRow.createCell(2).setCellValue(citizenPlan.getPlanStatus());
			dataRow.createCell(3).setCellValue(citizenPlan.getCName());
			dataRow.createCell(4).setCellValue(citizenPlan.getCEmail());
			dataRow.createCell(5).setCellValue(citizenPlan.getGender());
			dataRow.createCell(6).setCellValue(citizenPlan.getPhone());
			dataRow.createCell(7).setCellValue(citizenPlan.getSsn());
			DataRowInfo++;
		}
		ServletOutputStream ops = response.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}

	@Override
	public void exportPdf(HttpServletResponse response) throws DocumentException, IOException {
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		Paragraph p = new Paragraph("Citizen Plain Info", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
		
		
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.0f, 3.5f, 3.0f,  5.0f, 3.0f, 2.0f,2.0f });
		table.setSpacingBefore(10);
		
		// set Table Header 
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(8);
		Font fHeader = FontFactory.getFont(FontFactory.HELVETICA);
		fHeader.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("ID", fHeader));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Name", fHeader));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Plan Status", fHeader));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Name", fHeader));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Email", fHeader));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Gender", fHeader));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Phone", fHeader));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("SSN", fHeader));
		table.addCell(cell);     
		
		// set Table Data 
		
		List<CitizenPlan> records = reportRepo.findAll();
		for (CitizenPlan record : records) {
			table.addCell(String.valueOf(record.getCId()));
            table.addCell(record.getPlanName());
            table.addCell(record.getPlanStatus());
            table.addCell(record.getCName());
            table.addCell(record.getCEmail());
            table.addCell(record.getGender());
            table.addCell(String.valueOf(record.getPhone()));
            table.addCell(String.valueOf(record.getSsn()));
		}
		document.add(table);
		document.close();
	}

}
