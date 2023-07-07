package com.cob.feedback.model.chart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ChartResponse {
    private Double[][] hospitalityChartData;
    private Double[][] clinicalChartData;
}
