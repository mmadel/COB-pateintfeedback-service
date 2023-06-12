package com.cob.feedback.service;

public interface FeedbackService {
    long retrieveHappyIndex(long dateFrom, long dateTo, long clinicId);

    long retrieveNPS(long dateFrom, long dateTo, long clinicId);

    long retrieveAverage(long dateFrom, long dateTo, long clinicId);
}
