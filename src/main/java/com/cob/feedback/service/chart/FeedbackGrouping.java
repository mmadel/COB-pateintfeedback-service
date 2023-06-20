package com.cob.feedback.service.chart;

import com.cob.feedback.model.FeedbackQuestion;
import com.cob.feedback.model.performance.chart.ChartDataContainer;
import com.cob.feedback.model.performance.chart.ChartResult;
import com.google.gson.Gson;

import java.math.BigInteger;

import static java.util.stream.Collectors.groupingBy;

public class FeedbackGrouping {
    public static void group(ChartDataContainer chartDataContainer) {
        chartDataContainer.setGroupedData(
                chartDataContainer.getPlainData().stream().map(resultSet -> {
                    return ChartResult.builder()
                            .time(new BigInteger(resultSet[0].toString()).longValue())
                            .feedbackValue(new Gson().fromJson(resultSet[1].toString(), FeedbackQuestion.class))
                            .build();
                }).collect(groupingBy(chartResult -> {
                    return chartResult.getGroupedValue(chartDataContainer.getChartTimeUnit());
                })));
    }
}
