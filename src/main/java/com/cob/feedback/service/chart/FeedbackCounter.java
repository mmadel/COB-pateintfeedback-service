package com.cob.feedback.service.chart;

import com.cob.feedback.model.chart.ChartDataContainer;
import com.cob.feedback.model.chart.ChartResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackCounter {
    public static void count(ChartDataContainer chartDataContainer) {
        Map<Long, long[][]> countedData = new HashMap<>();
        chartDataContainer.getGroupedData().entrySet().stream()
                .forEach(entry -> {
                    countedData.put(entry.getKey(), sumServiceFeedback(entry.getValue()));
                });
        chartDataContainer.setCountedData(countedData);
    }

    private static long[][] sumServiceFeedback(List<ChartResult> data) {
        long[][] feedbackCounters = new long[2][4];
        data.forEach(chartResult -> {
            switch (chartResult.getFeedbackValue().getHospitalityFeedback()) {
                case VGood:
                    feedbackCounters[0][0]++;
                    break;
                case Good:
                    feedbackCounters[0][1]++;
                    break;
                case Sad:
                    feedbackCounters[0][2]++;
                    break;
                case VSad:
                    feedbackCounters[0][3]++;
                    break;
            }
            switch (chartResult.getFeedbackValue().getClinicalFeedback()) {
                case VGood:
                    feedbackCounters[1][0]++;
                    break;
                case Good:
                    feedbackCounters[1][1]++;
                    break;
                case Sad:
                    feedbackCounters[1][2]++;
                    break;
                case VSad:
                    feedbackCounters[1][3]++;
                    break;
            }

        });
        return feedbackCounters;
    }
}
