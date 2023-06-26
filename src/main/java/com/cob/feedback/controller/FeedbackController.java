package com.cob.feedback.controller;

import com.cob.feedback.model.Feedback;
import com.cob.feedback.service.FeedbackCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "feedback")
public class FeedbackController {

    @Autowired
    FeedbackCreatorService creatorService;

    @PostMapping("/submit")
    @ResponseBody
    public ResponseEntity create(@RequestBody Feedback model) {
        return new ResponseEntity(creatorService.create(model), HttpStatus.OK);
    }
}
