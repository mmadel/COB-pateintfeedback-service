package com.cob.feedback.model.counters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class HospitalityCounterContainer {
    private long veryPositive;
    private long positive;
    private long negative;
    private long veryNegative;
    private long total;
    private double veryPositivePercentage;
    private double positivePercentage;
    private double negativePercentage;
    private double veryNegativePercentage;

}
