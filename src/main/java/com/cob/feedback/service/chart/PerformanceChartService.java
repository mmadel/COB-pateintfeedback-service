package com.cob.feedback.service.chart;

import com.cob.feedback.enums.ChartTimeUnit;
import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.model.performance.chart.ChartDataContainer;
import com.cob.feedback.model.performance.chart.ChartResponse;
import com.cob.feedback.repository.performance.PerformanceChartRepository;
import com.cob.feedback.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceChartService {
    @Autowired
    PerformanceChartRepository performanceChartRepository;

    public ChartTimeUnit chartTimeUnit;

    public ChartResponse getChartData(Long startDate, Long endDate, Long clinicId) {
        ChartDataContainer chartDataContainer = new ChartDataContainer();
        chartDataContainer.setPlainData(performanceChartRepository.get(startDate, endDate, clinicId));
        FeedbackGrouping.group(chartDataContainer);
        FeedbackCounter.count(chartDataContainer);
        FeedbackFormulaCalculator.calculate(chartDataContainer);

        return ChartResponse.builder()
                .hospitalityChartData(getServicePerformanceIndex(chartDataContainer, ServiceName.HOSPITALITY))
                .clinicalChartData(getServicePerformanceIndex(chartDataContainer, ServiceName.CLINICAL))
                .build();
    }

    private Double[][] getServicePerformanceIndex(ChartDataContainer chartDataContainer, ServiceName serviceName) {

        Double[][] hospitalityServicePerformanceIndex = new Double[3][];
        Double[] happyIndex = TimeUtils.getTimeScale(chartTimeUnit);
        Double[] nps = TimeUtils.getTimeScale(chartTimeUnit);
        Double[] average = TimeUtils.getTimeScale(chartTimeUnit);

        chartDataContainer.getCalculatedData().entrySet()
                .forEach(resultSet -> {
                    switch (serviceName) {
                        case HOSPITALITY:
                            happyIndex[resultSet.getKey().intValue()] = resultSet.getValue()[0][0];
                            nps[resultSet.getKey().intValue()] = resultSet.getValue()[0][1];
                            average[resultSet.getKey().intValue()] = resultSet.getValue()[0][2];
                            break;
                        case CLINICAL:
                            happyIndex[resultSet.getKey().intValue()] = resultSet.getValue()[1][0];
                            nps[resultSet.getKey().intValue()] = resultSet.getValue()[1][1];
                            average[resultSet.getKey().intValue()] = resultSet.getValue()[1][2];
                    }

                });
        hospitalityServicePerformanceIndex[0] = happyIndex;
        hospitalityServicePerformanceIndex[1] = nps;
        hospitalityServicePerformanceIndex[2] = average;
        return hospitalityServicePerformanceIndex;
    }

}
