package com.cob.feedback.controller;

import com.cob.feedback.excpetion.business.UserException;
import com.cob.feedback.model.admin.user.UserModel;
import com.cob.feedback.service.user.UserCreatorService;
import com.cob.feedback.service.user.UserFinderService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
    public ResponseEntity update(@RequestBody UserModel model) throws UserException {
        return new ResponseEntity(creator.update(model), HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/delete/userId/{userId}")
    public ResponseEntity delete(@PathVariable long userId) {
        creator.delete(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile file) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        creator.upload(workbook);
        workbook.close();
        file.getInputStream().close();
        return new ResponseEntity(HttpStatus.OK);
    }
}
