package com.cob.feedback.model.counters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CountersContainer {
    private HospitalityCounterContainer hospitalityCounterContainer;
    private ClinicalCountersContainer clinicalCountersContainer;
}
