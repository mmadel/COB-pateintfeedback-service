package com.cob.feedback.service;

import org.springframework.stereotype.Service;

@Service
public class ClinicalService  implements FeedbackService{
    @Override
    public long retrieveHappyIndex(long dateFrom, long dateTo, long clinicId) {
        return 0;
    }

    @Override
    public long retrieveNPS(long dateFrom, long dateTo, long clinicId) {
        return 0;
    }

    @Override
    public double retrieveAverage(long dateFrom, long dateTo, long clinicId) {
        return 0;
    }
}
