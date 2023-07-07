package com.cob.feedback.service.chart;

import com.cob.feedback.formula.AverageFormula;
import com.cob.feedback.formula.HappyIndexFormula;
import com.cob.feedback.formula.NPSFormula;
import com.cob.feedback.model.chart.ChartDataContainer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FeedbackFormulaCalculator {

    public static void calculate(ChartDataContainer chartDataContainer) {
        Map<Long, double[][]> formulatedData = new HashMap<>();
        chartDataContainer.getCountedData().entrySet().stream().forEach(entry -> {
            double[][] result = new double[2][3];
            long[] hospitalityCounter = entry.getValue()[0];
            long[] clinicalCounter = entry.getValue()[1];
            result[0] = calculatePerformanceIndex(hospitalityCounter);
            result[1] = calculatePerformanceIndex(clinicalCounter);
            formulatedData.put(entry.getKey(), result);
        });
        chartDataContainer.setCalculatedData(formulatedData);
    }

    private static double[] calculatePerformanceIndex(long[] counters) {
        double[] servicePerformanceIndex = new double[3];
        servicePerformanceIndex[0] = HappyIndexFormula.calculate(100, counters[0], counters[1], counters[1], counters[3]);
        servicePerformanceIndex[1] = NPSFormula.calculate(Arrays.stream(counters).sum(), counters[0], counters[2], counters[3]);
        servicePerformanceIndex[2] = AverageFormula.calculate(counters[0], counters[1], counters[2], counters[3]);
        return servicePerformanceIndex;
    }
}
