package com.report.comm;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.report.model.CitizenPlan;

@Component
public class CitizenComm implements Runnable{

	
	@Bean
	@Override
	public void run() throws Exception {
		 CitizenPlan plan = new CitizenPlan(); 
		 plan.setPlanName("SSC");
		 plan.setPlanStatus("Active");
		 plan.setCName("Irfan");
		 plan.setCEmail("irfan@gmail.com");
		 plan.setGender("Male");
		 plan.setPhone(6626);
		 plan.setSsn(51515);
	}
	
}
