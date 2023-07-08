package com.cob.feedback.service;

import com.cob.feedback.enums.FeedbackFeeling;
import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.formula.FormulaUtils;
import com.cob.feedback.model.counters.DBCounterContainer;
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

        /*
         - Hospitality OR(based on service name argument) Clinical
            int[0] -> count of very positive
            int[1] -> count of positive
            int[2] -> count of negative
            int[3] -> count of very negative
            total:Hospitality = int[0] + int[1] + int[2] + int[3]
            total:Clinical = int[0] + int[1] + int[2] + int[3]
         */
        return feedbackValues;
    }

    public static DBCounterContainer countWithPercentage(long dateFrom, long dateTo, long clinicId, ServiceName serviceName) {
        DBCounterContainer dbCounterContainer = new DBCounterContainer();
        int[] counter = count(dateFrom, dateTo, clinicId, serviceName);
        double[] percentage = new double[4];
        int total = counter[0] + counter[1] + counter[2] + counter[3];
        percentage[0] = FormulaUtils.calculatePercentage(counter[0], total);
        percentage[1] = FormulaUtils.calculatePercentage(counter[1], total);
        percentage[2] = FormulaUtils.calculatePercentage(counter[2], total);
        percentage[3] = FormulaUtils.calculatePercentage(counter[3], total);
        dbCounterContainer.setCounters(counter);
        dbCounterContainer.setPercentages(percentage);
        return dbCounterContainer;
    }
}
