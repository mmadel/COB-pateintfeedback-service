package com.cob.feedback.controller;

import com.cob.feedback.service.dashboard.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;
    @GetMapping("/find/startDate/{startDate}/endDate/{endDate}")
    @ResponseBody
    public ResponseEntity findByDateRang(@PathVariable("startDate") Long startDate,
                                         @PathVariable("endDate") Long endDate){
        return new ResponseEntity(dashboardService.findByDateRange(startDate,endDate), HttpStatus.OK);
    }
    @GetMapping("/find/numbers/startDate/{startDate}/endDate/{endDate}")
    @ResponseBody
    public ResponseEntity findFeedbackNumbers(@PathVariable("startDate") Long startDate,
                                              @PathVariable("endDate") Long endDate){
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
