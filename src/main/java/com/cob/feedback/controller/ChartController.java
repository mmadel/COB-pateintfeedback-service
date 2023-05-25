package com.cob.feedback.controller;

import com.cob.feedback.service.dashboard.ChartYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dashboard/chart")
public class ChartController {

    @Autowired
    ChartYearService chartYearService;
    @GetMapping("/find/startDate/{startDate}/endDate/{endDate}")
    @ResponseBody
    public ResponseEntity findFeedbackInYear(@PathVariable("startDate") Long startDate,
                                         @PathVariable("endDate") Long endDate){
        return new ResponseEntity(chartYearService.get(startDate,endDate), HttpStatus.OK);
    }
}
