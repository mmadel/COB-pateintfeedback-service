package com.cob.feedback.controller;

import com.cob.feedback.service.clinic.ClinicServiceFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "clinic")
public class ClinicController {

    @Autowired
    ClinicServiceFinder finder;
    @GetMapping(path = "/find/userId/{userId}")
    @ResponseBody
    public ResponseEntity getUserClinics(@PathVariable Long userId) {
        return new ResponseEntity(finder.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/find")
    @ResponseBody
    public ResponseEntity getAll() {
        return new ResponseEntity(finder.find(), HttpStatus.OK);
    }
}
