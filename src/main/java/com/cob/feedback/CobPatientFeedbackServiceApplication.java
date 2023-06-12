package com.cob.feedback;

import com.cob.feedback.service.HospitalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CobPatientFeedbackServiceApplication  implements CommandLineRunner {
	@Autowired
	HospitalityService service;
	public static void main(String[] args) {
		SpringApplication.run(CobPatientFeedbackServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.retrieveHappyIndex(1685610000000L,1688158799000L,1);
	}
}
