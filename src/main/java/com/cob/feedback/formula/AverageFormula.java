package com.cob.feedback.formula;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static com.cob.feedback.formula.FormulaConstants.FEEDBACK_WIGHT;

public class AverageFormula {
    private static final DecimalFormat df = new DecimalFormat("0.0");

    public static double calculate(double vPositiveValue, double positiveValue,
                                   double negativeValue) {
        double numerator = vPositiveValue * FEEDBACK_WIGHT[0]
                + positiveValue * FEEDBACK_WIGHT[1]
                + negativeValue * FEEDBACK_WIGHT[2];
        double denominator = vPositiveValue + positiveValue + negativeValue ;
        Double result = numerator / denominator;
        BigDecimal averageResult = new BigDecimal(result.isNaN() ? 0.0 : result).setScale(2, RoundingMode.UP);
        return averageResult.doubleValue();

    }
}
