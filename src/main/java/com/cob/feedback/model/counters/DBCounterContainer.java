package com.cob.feedback.model.counters;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DBCounterContainer {
    int[] counters;
    double[] percentages;
}
