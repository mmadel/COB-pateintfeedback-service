package com.cob.feedback;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.model.performance.PerformanceIndexContainer;
import com.cob.feedback.service.FeedbackRetrievalService;
import com.cob.feedback.service.PerformanceFeedbackService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CobPatientFeedbackServiceApplication implements CommandLineRunner {
    @Autowired
    FeedbackRetrievalService feedbackRetrievalService;

    public static void main(String[] args) {
        SpringApplication.run(CobPatientFeedbackServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PerformanceIndexContainer result = feedbackRetrievalService.retrieve(1685610000000L, 1688158799000L, 1);
        Gson gson = new Gson();
        System.out.println(gson.toJson(result));

    }
}
