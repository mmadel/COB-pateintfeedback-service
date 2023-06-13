package com.cob.feedback.service;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.model.performance.ClinicalContainer;
import com.cob.feedback.model.performance.HospitalityContainer;
import com.cob.feedback.model.performance.PerformanceIndexContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackRetrievalService {
    @Autowired
    PerformanceFeedbackService performanceFeedbackService;
    long startDate;
    long endDate;
    long clinicalId;

    public PerformanceIndexContainer retrieve(long startDate, long endDate, long clinicId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.clinicalId = clinicId;
        return PerformanceIndexContainer.builder()
                .hospitalityContainer(buildHospitalityPerformanceIndex())
                .clinicalContainer(buildClinicalPerformanceIndex())
                .build();

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
