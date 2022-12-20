package com.report.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="REPORT-API")
public class Report {
	@Id
	@GeneratedValue
	private Integer reportId;
	private String reportName;
	private String reportEmail;
	private Long reportPhone;
	private String reportGender;
	private Long reportSSN;
}
