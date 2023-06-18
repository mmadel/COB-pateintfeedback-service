package com.cob.feedback.model.performance.chart;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ChartDataContainer {

    List<Object[]> plainData;
    private Map<Long, List<ChartResult>> groupedData;
    private Map<Long, long[][]> countedData;

    Map<Long, double[][]> calculatedData;

}
