package com.cob.feedback.utils;

public class PercentageCalculator {
    public static double calculatePercentage(int totalNumberOfPatients, int toBeCalculated) {
        return totalNumberOfPatients == 0 ? 0 : NumberCalculator.round((toBeCalculated * 100) / totalNumberOfPatients, 2);
    }
}
