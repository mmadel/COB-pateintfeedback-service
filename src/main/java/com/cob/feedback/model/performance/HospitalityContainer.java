package com.cob.feedback.model.performance;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class HospitalityContainer {
    private long happyIndex;
    private long nps;
    private double average;

    @Override
    public String toString() {
        return "HospitalityContainer{" +
                "happyIndex=" + happyIndex +
                ", nps=" + nps +
                ", average=" + average +
                '}';
    }
}
