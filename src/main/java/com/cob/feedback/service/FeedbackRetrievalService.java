package com.cob.feedback.service;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.excpetion.business.FeedbackPerformanceException;
import com.cob.feedback.model.performance.ClinicalContainer;
import com.cob.feedback.model.performance.HospitalityContainer;
import com.cob.feedback.model.performance.PerformanceIndexContainer;
import com.cob.feedback.utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FeedbackRetrievalService {
    @Autowired
    PerformanceFeedbackService performanceFeedbackService;
    long startDate;
    long endDate;
    long clinicalId;

    public PerformanceIndexContainer retrieve(long startDate, long endDate, long clinicId) throws FeedbackPerformanceException {
        this.startDate = startDate;
        this.endDate = endDate;
        this.clinicalId = clinicId;
        PerformanceIndexContainer result = PerformanceIndexContainer.builder()
                .hospitalityContainer(buildHospitalityPerformanceIndex())
                .clinicalContainer(buildClinicalPerformanceIndex())
                .build();
        /*if (result.isEmptyPerformanceContainer())
            throw new FeedbackPerformanceException(HttpStatus.CONFLICT, FeedbackPerformanceException.PERFORMANCE_INDEX_IS_EMPTY,
                    new Object[]{
                            clinicId, DateFormatter.formatTimeStampAsString(startDate),
                            DateFormatter.formatTimeStampAsString(endDate)});*/
        return result;
    }

    private HospitalityContainer buildHospitalityPerformanceIndex() {
        performanceFeedbackService.serviceName = ServiceName.HOSPITALITY;
        return HospitalityContainer.builder()
                .happyIndex(performanceFeedbackService.retrieveHappyIndex(startDate, endDate, clinicalId))
                .nps(performanceFeedbackService.retrieveNPS(startDate, endDate, clinicalId))
                .average(performanceFeedbackService.retrieveAverage(startDate, endDate, clinicalId))
                .build();

    }

    private ClinicalContainer buildClinicalPerformanceIndex() {
        performanceFeedbackService.serviceName = ServiceName.CLINICAL;
        return ClinicalContainer.builder()
                .happyIndex(performanceFeedbackService.retrieveHappyIndex(startDate, endDate, clinicalId))
                .nps(performanceFeedbackService.retrieveNPS(startDate, endDate, clinicalId))
                .average(performanceFeedbackService.retrieveAverage(startDate, endDate, clinicalId))
                .build();
    }
}
