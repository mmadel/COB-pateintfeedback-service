package com.cob.feedback.controller;

import com.cob.feedback.enums.ChartTimeUnit;
import com.cob.feedback.excpetion.business.FeedbackPerformanceException;
import com.cob.feedback.service.Performance.PerformanceFeedbackBuilder;
import com.cob.feedback.service.chart.PerformanceChartService;
import com.cob.feedback.service.counters.CounterFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "performance")
public class PerformanceIndexController {
    @Autowired
    PerformanceFeedbackBuilder performanceFeedbackBuilder;
    @Autowired
    PerformanceChartService performanceChartService;
    @Autowired
    CounterFeedbackService counterFeedbackService;


    @GetMapping(value = "/get/startDate/{startDate}/endDate/{endDate}/clinicId/{clinicId}")
    public ResponseEntity get(@PathVariable("clinicId") Long clinicId,
                              @PathVariable("startDate") Long startDate,
                              @PathVariable("endDate") Long endDate) throws FeedbackPerformanceException {
        return new ResponseEntity(performanceFeedbackBuilder.retrieve(startDate, endDate, clinicId), HttpStatus.OK);
    }

    @GetMapping(value = "/get/chart/startDate/{startDate}/endDate/{endDate}/clinicId/{clinicId}/chartTimeUnit/{chartTimeUnit}")
    public ResponseEntity getChartData(@PathVariable("clinicId") Long clinicId,
                                       @PathVariable("startDate") Long startDate,
                                       @PathVariable("endDate") Long endDate,
                                       @PathVariable("chartTimeUnit") ChartTimeUnit chartTimeUnit) {
        performanceChartService.chartTimeUnit = chartTimeUnit;
        return new ResponseEntity(performanceChartService.retrieve(startDate, endDate, clinicId), HttpStatus.OK);
    }

    @GetMapping(value = "/get/counters/startDate/{startDate}/endDate/{endDate}/clinicId/{clinicId}")
    public ResponseEntity getServiceCounterData(@PathVariable("clinicId") Long clinicId,
                                                @PathVariable("startDate") Long startDate,
                                                @PathVariable("endDate") Long endDate) {
        return new ResponseEntity(counterFeedbackService.retrieve(startDate, endDate, clinicId), HttpStatus.OK);
    }
}
