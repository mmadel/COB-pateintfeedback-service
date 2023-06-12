package com.cob.feedback.service;

import com.cob.feedback.BeanFactory;
import com.cob.feedback.enums.FeedbackFeeling;
import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.formula.AverageFormula;
import com.cob.feedback.formula.HappyIndexFormula;
import com.cob.feedback.formula.NPSFormula;
import com.cob.feedback.repository.performance.ClinicalFeedbackPerformanceRepository;
import com.cob.feedback.repository.performance.HospitalityFeedbackPerformanceRepository;
import com.cob.feedback.repository.performance.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PerformanceFeedbackService {
    public ServiceName serviceName;
    public long retrieveHappyIndex(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = getFeedbackNumbers(dateFrom, dateTo, clinicId);
        return HappyIndexFormula.calculate(100, feedbackValues[0], feedbackValues[1], feedbackValues[1], feedbackValues[3]);

    }


    public long retrieveNPS(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = getFeedbackNumbers(dateFrom, dateTo, clinicId);
        long total = Arrays.stream(feedbackValues).sum();
        return NPSFormula.calculate(total, feedbackValues[0], feedbackValues[2], feedbackValues[3]);
    }


    public double retrieveAverage(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = getFeedbackNumbers(dateFrom, dateTo, clinicId);
        return AverageFormula.calculate(feedbackValues[0], feedbackValues[1], feedbackValues[2], feedbackValues[3]);
    }

    public int[] getFeedbackNumbers(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = new int[4];
        int counter = 0;
        PerformanceRepository performanceRepository = null;
        switch (serviceName) {
            case HOSPITALITY:
                performanceRepository = BeanFactory.getBean(HospitalityFeedbackPerformanceRepository.class);
                break;
            case CLINICAL:
                performanceRepository = BeanFactory.getBean(ClinicalFeedbackPerformanceRepository.class);
                break;
        }
        for (FeedbackFeeling feeling : FeedbackFeeling.values()) {
            feedbackValues[counter++] = performanceRepository.count(dateFrom, dateTo, clinicId, feeling.label);
        }
        return feedbackValues;
    }
}
