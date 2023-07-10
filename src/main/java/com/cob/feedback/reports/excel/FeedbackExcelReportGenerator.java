package com.cob.feedback.reports.excel;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.model.reports.ExcelReportResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FeedbackExcelReportGenerator {
    public void export(HttpServletResponse response, String[] columns, Map<ServiceName, List<ExcelReportResponse>> data) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        data.forEach((serviceName, excelReportResponse) -> {
            String sheetName = serviceName.name().substring(0, 1).toUpperCase() +  serviceName.name().substring(1).toLowerCase();
            ExcelDataGenerator.createHeaderLine(workbook, columns,sheetName);
            ExcelDataGenerator.createDataLines(workbook, excelReportResponse);
        });

        ServletOutputStream outputStream = fillHTTPResponse(response).getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    private static HttpServletResponse fillHTTPResponse(HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=report_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        return response;
    }
}
