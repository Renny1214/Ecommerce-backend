package com.caseStudy.caseStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.jws.soap.SOAPBinding;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CaseStudyApplication {


	public static void main(String[] args) {
		SpringApplication.run(CaseStudyApplication.class, args);
	}

//	@Bean
//	public CorsFilter corsFilter() {
//
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		final CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.setAllowedOrigins(Collections.singletonList("*"));
//		config.setAllowedHeaders(Arrays.asList("Origin","Content-Type","Accept"));
//		config.setAllowedMethods(Arrays.asList("GET","POST","PUT","OPTIONS","DELETE","PATCH"));
//		source.registerCorsConfiguration("/**",config);
//
//		return new CorsFilter(source);
	}

