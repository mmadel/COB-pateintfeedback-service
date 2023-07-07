package com.cob.feedback.service.counters;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.model.counters.ClinicalCountersContainer;
import com.cob.feedback.model.counters.CountersContainer;
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
        int[] counters = FeedbackCounter.count(startDate, endDate, clinicId, ServiceName.HOSPITALITY);
        return HospitalityCounterContainer.builder()
                .veryPositive(counters[0])
                .positive(counters[1])
                .negative(counters[2])
                .veryNegative(counters[3])
                .build();


    }

    private ClinicalCountersContainer getClinicalCounters() {
        int[] counters = FeedbackCounter.count(startDate, endDate, clinicId, ServiceName.CLINICAL);
        return ClinicalCountersContainer.builder()
                .veryPositive(counters[0])
                .positive(counters[1])
                .negative(counters[2])
                .veryNegative(counters[3])
                .build();
    }
}
