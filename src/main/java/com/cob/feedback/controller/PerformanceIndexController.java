package com.cob.feedback.controller;

import com.cob.feedback.enums.ChartTimeUnit;
import com.cob.feedback.excpetion.business.FeedbackPerformanceException;
import com.cob.feedback.excpetion.response.ControllerErrorResponseAdvisor;
import com.cob.feedback.service.FeedbackRetrievalService;
import com.cob.feedback.service.chart.PerformanceChartService;
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
    FeedbackRetrievalService feedbackRetrievalService;
    @Autowired
    PerformanceChartService performanceChartService;
    @Autowired
    ControllerErrorResponseAdvisor controllerErrorResponseAdvisor;

    @GetMapping(value = "/get/startDate/{startDate}/endDate/{endDate}/clinicId/{clinicId}")
    public ResponseEntity get(@PathVariable("clinicId") Long clinicId,
                              @PathVariable("startDate") Long startDate,
                              @PathVariable("endDate") Long endDate) {
        try {
            return new ResponseEntity(feedbackRetrievalService.retrieve(startDate, endDate, clinicId), HttpStatus.OK);
        } catch (FeedbackPerformanceException performanceException) {
            return controllerErrorResponseAdvisor.responseError(performanceException);
        }
    }

    @GetMapping(value = "/get/chart/startDate/{startDate}/endDate/{endDate}/clinicId/{clinicId}/chartTimeUnit/{chartTimeUnit}")
    public ResponseEntity getChartData(@PathVariable("clinicId") Long clinicId,
                                       @PathVariable("startDate") Long startDate,
                                       @PathVariable("endDate") Long endDate,
                                       @PathVariable("chartTimeUnit") ChartTimeUnit chartTimeUnit) {
        performanceChartService.chartTimeUnit = chartTimeUnit;
        return new ResponseEntity(performanceChartService.getChartData(startDate, endDate, clinicId), HttpStatus.OK);
    }

}
