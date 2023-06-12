package com.cob.feedback.formula;

import static com.cob.feedback.formula.FormulaUtils.calculatePercentage;

public class NPSFormula {
    public static double calculate(long total, long vPositiveValue, long negativeValue, long vNegativeValue) {
        return calculatePercentage(vPositiveValue, total)
                - calculatePercentage(negativeValue, total)
                - calculatePercentage(vNegativeValue, total);
    }
}
