package com.cob.feedback.controller;

import com.cob.feedback.enums.FeedbackFeeling;
import com.cob.feedback.service.dashboard.clinical.ClinicalChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clinical/chart")
public class ClinicalChartController {

    @Autowired
    ClinicalChartService clinicalChartService;
    @GetMapping("/find/counters/startDate/{startDate}/endDate/{endDate}")
    @ResponseBody
    public ResponseEntity findFeedbackCounters(@PathVariable("startDate") Long startDate,
                                               @PathVariable("endDate") Long endDate){
        System.out.println(FeedbackFeeling.VSad);
        clinicalChartService.countByFeelingAndDateRange(startDate,endDate,1L, FeedbackFeeling.Sad.name());
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
