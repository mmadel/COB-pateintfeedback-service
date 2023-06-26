package com.cob.feedback.model.performance.chart;

import com.cob.feedback.enums.ChartTimeUnit;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ChartDataContainer {

    public ChartDataContainer(ChartTimeUnit chartTimeUnit){
        this.chartTimeUnit = chartTimeUnit;
    }
    List<Object[]> plainData;
    private Map<Long, List<ChartResult>> groupedData;
    private Map<Long, long[][]> countedData;

    Map<Long, double[][]> calculatedData;
    ChartTimeUnit chartTimeUnit;

}
