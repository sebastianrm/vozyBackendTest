package com.srm.vozyBackendTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VozyBackendTestApplication {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VozyBackendTestApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(VozyBackendTestApplication.class, args);
	}
}
