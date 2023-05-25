package com.cob.feedback.controller;

import com.cob.feedback.service.dashboard.hospitality.HospitalityChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/hospitality/chart")
public class HospitalityChartController {
    @Autowired
    HospitalityChartService hospitalityChartService;
    @GetMapping("/find/counters/entityName/{entityName}")
    @ResponseBody
    public ResponseEntity findFeedbackCounters(@PathVariable("entityName") String entityName){
        hospitalityChartService.test(entityName);
        return new ResponseEntity(null, HttpStatus.OK);
    }
}
