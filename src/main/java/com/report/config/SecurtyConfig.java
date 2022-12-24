package com.report.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurtyConfig {
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((request) -> request
				.antMatchers("/planname","/plainstatus","/excle","/pdf").permitAll()
				.anyRequest().authenticated()
		).formLogin();
		return http.build();
		
	}
}
