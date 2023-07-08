package com.cob.feedback.service.counters;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.model.counters.ClinicalCountersContainer;
import com.cob.feedback.model.counters.CountersContainer;
import com.cob.feedback.model.counters.DBCounterContainer;
import com.cob.feedback.model.counters.HospitalityCounterContainer;
import com.cob.feedback.service.FeedbackCounter;
import org.springframework.stereotype.Service;

@Service
public class CounterFeedbackService {
    private long startDate;
    private long endDate;
    private long clinicId;

    public CountersContainer retrieve(long startDate, long endDate, long clinicId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.clinicId = clinicId;
        return CountersContainer.builder()
                .hospitalityCounterContainer(getHospitalityCounters())
                .clinicalCountersContainer(getClinicalCounters())
                .build();
    }

    private HospitalityCounterContainer getHospitalityCounters() {
        DBCounterContainer dbCounterContainer = FeedbackCounter.countWithPercentage(startDate, endDate, clinicId, ServiceName.HOSPITALITY);
        return HospitalityCounterContainer.builder()
                .veryPositive(dbCounterContainer.getCounters()[0])
                .positive(dbCounterContainer.getCounters()[1])
                .negative(dbCounterContainer.getCounters()[2])
                .veryNegative(dbCounterContainer.getCounters()[3])
                .veryPositivePercentage(dbCounterContainer.getPercentages()[0])
                .positivePercentage(dbCounterContainer.getPercentages()[1])
                .negativePercentage(dbCounterContainer.getPercentages()[2])
                .veryNegativePercentage(dbCounterContainer.getPercentages()[3])
                .build();


    }

    private ClinicalCountersContainer getClinicalCounters() {
        DBCounterContainer dbCounterContainer = FeedbackCounter.countWithPercentage(startDate, endDate, clinicId, ServiceName.CLINICAL);
        return ClinicalCountersContainer.builder()
                .veryPositive(dbCounterContainer.getCounters()[0])
                .positive(dbCounterContainer.getCounters()[1])
                .negative(dbCounterContainer.getCounters()[2])
                .veryNegative(dbCounterContainer.getCounters()[3])
                .veryPositivePercentage(dbCounterContainer.getPercentages()[0])
                .positivePercentage(dbCounterContainer.getPercentages()[1])
                .negativePercentage(dbCounterContainer.getPercentages()[2])
                .veryNegativePercentage(dbCounterContainer.getPercentages()[3])
                .build();
    }
}
