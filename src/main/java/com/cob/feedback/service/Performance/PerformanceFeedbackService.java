package com.cob.feedback.service.Performance;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.formula.AverageFormula;
import com.cob.feedback.formula.HappyIndexFormula;
import com.cob.feedback.formula.NPSFormula;
import com.cob.feedback.service.FeedbackCounter;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PerformanceFeedbackService {
    public ServiceName serviceName;

    public long retrieveHappyIndex(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = FeedbackCounter.count(dateFrom, dateTo, clinicId,serviceName);
        return HappyIndexFormula.calculate(100, feedbackValues[0], feedbackValues[1], feedbackValues[1], feedbackValues[3]);

    }


    public long retrieveNPS(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = FeedbackCounter.count(dateFrom, dateTo, clinicId,serviceName);
        long total = Arrays.stream(feedbackValues).sum();
        return NPSFormula.calculate(total, feedbackValues[0], feedbackValues[2], feedbackValues[3]);
    }


    public double retrieveAverage(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = FeedbackCounter.count(dateFrom, dateTo, clinicId,serviceName);
        return AverageFormula.calculate(feedbackValues[0], feedbackValues[1], feedbackValues[2], feedbackValues[3]);
    }
}
