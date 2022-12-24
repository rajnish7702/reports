package com.report.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="CITIZEN_API_REPORTS")
@Data
public class CitizenPlan {
	@Id
	@GeneratedValue
	private Integer cId;
	private String planName;
	private String planStatus;
	private String cName;
	private String cEmail;
	private String gender;
	private Long phone;
	private Long ssn;
}
