package com.cob.feedback.service;

import com.cob.feedback.enums.FeedbackFeeling;
import com.cob.feedback.formula.AverageFormula;
import com.cob.feedback.formula.HappyIndexFormula;
import com.cob.feedback.formula.NPSFormula;
import com.cob.feedback.repository.HospitalityFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class HospitalityService implements FeedbackService {

    @Autowired
    HospitalityFeedbackRepository hospitalityFeedbackRepository;

    @Override
    public long retrieveHappyIndex(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = new int[4];
        int counter = 0;
        for (FeedbackFeeling feeling : FeedbackFeeling.values()) {
            feedbackValues[counter++] = hospitalityFeedbackRepository.count(dateFrom, dateTo, clinicId, feeling.label);
        }
        return HappyIndexFormula.calculate(100, feedbackValues[0], feedbackValues[1], feedbackValues[1], feedbackValues[3]);

    }

    @Override
    public long retrieveNPS(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = new int[4];
        int counter = 0;
        for (FeedbackFeeling feeling : FeedbackFeeling.values()) {
            feedbackValues[counter++] = hospitalityFeedbackRepository.count(dateFrom, dateTo, clinicId, feeling.label);
        }
        long total = Arrays.stream(feedbackValues).sum();
        return NPSFormula.calculate(total, feedbackValues[0], feedbackValues[2], feedbackValues[3]);
    }

    @Override
    public double retrieveAverage(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues = new int[4];
        int counter = 0;
        for (FeedbackFeeling feeling : FeedbackFeeling.values()) {
            System.out.println(feeling.label);
            feedbackValues[counter++] = hospitalityFeedbackRepository.count(dateFrom, dateTo, clinicId, feeling.label);
        }
        return AverageFormula.calculate(feedbackValues[0], feedbackValues[1], feedbackValues[2], feedbackValues[3]);
    }
}
