package com.cob.feedback.controller;

import com.cob.feedback.model.admin.user.UserModel;
import com.cob.feedback.service.user.UserCreatorService;
import com.cob.feedback.service.user.UserFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserFinderService finderService;
    @Autowired
    UserCreatorService creator;

    @GetMapping(path = "/find")
    @ResponseBody
    public ResponseEntity getAll() {
        return new ResponseEntity(finderService.find(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/userId/{userId}")
    @ResponseBody
    public ResponseEntity getById(@PathVariable long userId) {
        return new ResponseEntity(finderService.findById(userId), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody UserModel model) {
        return new ResponseEntity(creator.create(model), HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/delete/userId/{userId}")
    public ResponseEntity delete(@PathVariable long userId) {
        creator.delete(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
