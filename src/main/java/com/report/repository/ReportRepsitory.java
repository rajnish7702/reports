package com.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.report.model.CitizenPlan;

@Repository
public interface ReportRepsitory extends JpaRepository<CitizenPlan, Integer>{
	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getPlanNames();
	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();
}
