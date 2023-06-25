package com.cob.feedback.controller;

import com.cob.feedback.model.reports.ExcelReportCriteria;
import com.cob.feedback.reports.excel.FeedbackExcelReportGenerator;
import com.cob.feedback.service.reports.excel.ExcelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("reports")
public class FeedbackReportControllers {

    @Autowired
    ExcelReportService excelReportService;

    @PostMapping("/excel")
    public void generateExcel(@RequestBody ExcelReportCriteria reportCriteria, HttpServletResponse response) throws IOException {
        excelReportService.search(reportCriteria);
        FeedbackExcelReportGenerator feedbackExcelReportGenerator = new FeedbackExcelReportGenerator();
        feedbackExcelReportGenerator.export(response, excelReportService.getColumnsNames(),excelReportService.getData());
    }

    @PostMapping("/plain")
    public ResponseEntity generatePlain(@RequestBody ExcelReportCriteria reportCriteria) throws IOException {
        excelReportService.search(reportCriteria);
        return new ResponseEntity(excelReportService.getData(), HttpStatus.OK);
    }
}
