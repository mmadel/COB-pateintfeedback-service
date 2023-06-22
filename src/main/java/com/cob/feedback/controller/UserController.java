package com.cob.feedback.controller;

import com.cob.feedback.service.user.UserFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserFinderService finderService;
    @GetMapping(path = "/find")
    @ResponseBody
    public ResponseEntity getAll() {
        return new ResponseEntity(finderService.find(), HttpStatus.OK);
    }
}
