package com.cob.feedback.model.performance;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ClinicalContainer {
    private long happyIndex;
    private long nps;
    private double average;

    @Override
    public String toString() {
        return "ClinicalContainer{" +
                "happyIndex=" + happyIndex +
                ", nps=" + nps +
                ", average=" + average +
                '}';
    }
}
