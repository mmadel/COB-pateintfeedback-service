package com.cob.feedback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "counter")
public class FeedbackCounterController {

    @GetMapping(value = "/get/startDate/{startDate}/endDate/{endDate}/clinicId/{clinicId}")
    public ResponseEntity get(@PathVariable("clinicId") Long clinicId,
                              @PathVariable("startDate") Long startDate,
                              @PathVariable("endDate") Long endDate) {
        return null;
    }
}
