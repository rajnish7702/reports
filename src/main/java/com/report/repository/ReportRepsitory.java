package com.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.report.model.Report;

@Repository
public interface ReportRepsitory extends JpaRepository<Report, Integer>{

}
