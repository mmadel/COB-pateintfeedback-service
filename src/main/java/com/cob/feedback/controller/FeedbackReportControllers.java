package com.cob.feedback.controller;

import com.cob.feedback.excpetion.business.ReportingPerformanceException;
import com.cob.feedback.model.reports.ExcelReportCriteria;
import com.cob.feedback.service.reports.excel.ExcelReportService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("reports")
public class FeedbackReportControllers {

    @Autowired
    ExcelReportService excelReportService;

    @PostMapping("/excel")
    public void generateExcel(@RequestBody ExcelReportCriteria reportCriteria, HttpServletResponse response) throws IOException, ReportingPerformanceException {
        XSSFWorkbook workbook = excelReportService.export(reportCriteria);
        constructResponse(response);
        workbook.write(response.getOutputStream());
        workbook.close();
        response.getOutputStream().close();
    }

    private void constructResponse(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
    }
}
