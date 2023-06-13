package com.cob.feedback.controller;

import com.cob.feedback.service.FeedbackRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "performance/index")
public class PerformanceIndexController {
    @Autowired
    FeedbackRetrievalService feedbackRetrievalService;

    @GetMapping(value = "/get/startDate/{startDate}/endDate/{endDate}/clinicId/{clinicId}")
    public ResponseEntity get(@PathVariable("clinicId") Long clinicId,
                              @PathVariable("startDate") Long startDate,
                              @PathVariable("endDate") Long endDate) {
        return new ResponseEntity(feedbackRetrievalService.retrieve(startDate, endDate, clinicId), HttpStatus.OK);
    }
}