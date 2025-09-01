package com.cob.feedback.formula;

import org.apache.commons.lang3.math.Fraction;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.cob.feedback.formula.FormulaConstants.DEFAULT_STEP;

public class FormulaUtils {
    public static double calculateStep(int obtained, String value) {
        return Fraction.getFraction(value)
                .multiplyBy(Fraction.getFraction(obtained, DEFAULT_STEP)
                        .reduce()).doubleValue();
    }

    public static double calculatePercentage(double obtained, double total) {
        if(total == 0){
            return 0;
        }
        BigDecimal rounded =  new BigDecimal(obtained * 100 / total).setScale(2, RoundingMode.HALF_UP);
        return rounded.doubleValue();

    }
}
