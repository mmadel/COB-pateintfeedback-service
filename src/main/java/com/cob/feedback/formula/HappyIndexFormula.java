package com.cob.feedback.formula;

public class HappyIndexFormula {

    public static double calculate(int step, int vPositiveValue, int positiveValue,
                                   int negativeValue) {
        /*
            Formula to calculate the happy index
            HappinessIndex = ((AvgScore -Min) / (Max - Min)) /100
            Max is represents the wight of max scale : VGood : 3
            Min is represents the wight of min scale : VBad : 1
         */
        int totalResponses = vPositiveValue + positiveValue + negativeValue;
        if (totalResponses == 0) return 0.0;
        // Assign weights
        int vGoodWeight = 3;
        int goodWeight = 2;
        int badWeight = 1;
        // Calculate weighted average
        double averageScore = (
                (vPositiveValue * vGoodWeight) +
                        (positiveValue * goodWeight) +
                        (negativeValue * badWeight)
        ) / (double) totalResponses;

        // Normalize to 0–100
        double normalizedScore = ((averageScore - 1) / (3 - 1)) * 100;
        return Math.round(normalizedScore * 100.0) / 100.0;
    }
}
