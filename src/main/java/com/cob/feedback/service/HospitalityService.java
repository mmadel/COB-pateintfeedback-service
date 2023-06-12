package com.cob.feedback.service;

import com.cob.feedback.enums.FeedbackFeeling;
import com.cob.feedback.formula.HappyIndexFormula;
import com.cob.feedback.repository.HospitalityFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalityService implements FeedbackService {

    @Autowired
    HospitalityFeedbackRepository hospitalityFeedbackRepository;

    @Override
    public long retrieveHappyIndex(long dateFrom, long dateTo, long clinicId) {
        int[] feedbackValues =new int[4];
        int counter =0;
        for (FeedbackFeeling feeling : FeedbackFeeling.values()) {
            feedbackValues[counter++] = hospitalityFeedbackRepository.count(dateFrom, dateTo, clinicId,feeling.label);
        }
        return HappyIndexFormula.calculate(100, feedbackValues[0],feedbackValues[1],feedbackValues[1],feedbackValues[3]);

    }

    @Override
    public long retrieveNPS(long dateFrom, long dateTo, long clinicId) {
        return 0;
    }

    @Override
    public long retrieveAverage(long dateFrom, long dateTo, long clinicId) {
        return 0;
    }
}
