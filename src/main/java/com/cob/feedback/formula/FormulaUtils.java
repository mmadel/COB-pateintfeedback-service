package com.cob.feedback.formula;

import org.apache.commons.lang3.math.Fraction;

import static com.cob.feedback.formula.FormulaConstants.DEFAULT_STEP;

public class FormulaUtils {
    public static double calculateStep(int obtained, String value) {
        return Fraction.getFraction(value)
                .multiplyBy(Fraction.getFraction(obtained, DEFAULT_STEP)
                        .reduce()).doubleValue();
    }

    public static double calculatePercentage(double obtained, double total) {
        return Math.round(obtained * 100 / total);
    }
}
