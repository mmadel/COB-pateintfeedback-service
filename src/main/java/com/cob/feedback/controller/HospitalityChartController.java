package com.cob.feedback.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hospitality/chart")
public class HospitalityChartController {
    @GetMapping("/find/counters/startDate/{startDate}/endDate/{endDate}")
    @ResponseBody
    public ResponseEntity findFeedbackCounters(@PathVariable("startDate") Long startDate,
                                               @PathVariable("endDate") Long endDate){
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
