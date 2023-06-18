package com.cob.feedback.model.performance;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PerformanceIndexContainer {
    HospitalityContainer hospitalityContainer;
    ClinicalContainer clinicalContainer;

    public boolean isEmptyPerformanceContainer() {
        boolean hospitalityPerformance = hospitalityContainer.getHappyIndex() == 0 && hospitalityContainer.getNps() == 0 && hospitalityContainer.getAverage() == 0;
        boolean clinicalPerformance = clinicalContainer.getHappyIndex() == 0 && clinicalContainer.getNps() == 0 && clinicalContainer.getAverage() == 0;
        return hospitalityPerformance && clinicalPerformance;
    }

    @Override
    public String toString() {
        return "PerformanceIndexContainer{" +
                "hospitalityContainer=" + hospitalityContainer +
                ", clinicalContainer=" + clinicalContainer +
                '}';
    }
}
