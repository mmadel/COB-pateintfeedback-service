package com.cob.feedback.formula;

import static com.cob.feedback.formula.FormulaConstants.DEFAULT_NEGATIVE_FRACTION;
import static com.cob.feedback.formula.FormulaConstants.DEFAULT_POSITIVE_FRACTION;
import static com.cob.feedback.formula.FormulaUtils.calculateStep;

public class HappyIndexFormula {
    public static long calculate(int stepValue, double vPositiveValue, double positiveValue,
                                 double negativeValue, double vNegativeValue) {
        double numerator = (vPositiveValue * stepValue)
                + calculateStep(stepValue, DEFAULT_POSITIVE_FRACTION) * positiveValue
                + calculateStep(stepValue, DEFAULT_NEGATIVE_FRACTION) * negativeValue;
        double denominator = vPositiveValue + positiveValue + negativeValue + vNegativeValue;
        return Math.round(numerator / denominator);
    }
}
