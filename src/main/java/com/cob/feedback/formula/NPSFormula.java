package com.cob.feedback.formula;

import static com.cob.feedback.formula.FormulaUtils.calculatePercentage;

public class NPSFormula {
    public static long calculate(long total, long vPositiveValue, long negativeValue, long vNegativeValue) {
        return Math.round(calculatePercentage(vPositiveValue, total)
                - calculatePercentage(negativeValue, total)
                - calculatePercentage(vNegativeValue, total));
    }
}
