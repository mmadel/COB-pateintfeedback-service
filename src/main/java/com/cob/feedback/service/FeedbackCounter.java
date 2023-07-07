package com.cob.feedback.service;

import com.cob.feedback.enums.FeedbackFeeling;
import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.repository.ServiceFeedbackRepositoryBuilder;
import com.cob.feedback.repository.performance.PerformanceRepository;


public class FeedbackCounter {

    public static int[] count(long dateFrom, long dateTo, long clinicId, ServiceName serviceName) {
        int[] feedbackValues = new int[4];
        int counter = 0;
        PerformanceRepository performanceRepository = ServiceFeedbackRepositoryBuilder.build(serviceName);
        for (FeedbackFeeling feeling : FeedbackFeeling.values()) {
            feedbackValues[counter++] = performanceRepository.count(dateFrom, dateTo, clinicId, feeling.label);
        }
        return feedbackValues;
    }
}
