package com.cob.feedback.reports.excel;

import com.cob.feedback.model.reports.ExcelReportResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExcelGenerator {

    private String[] columns;


    @PostConstruct
    public void init() {
        setColumnsNames();
    }


    public void createSheet(XSSFWorkbook workbook, String sheetName) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        Row row = sheet.createRow(0);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        for (int i = 0; i < columns.length; i++) {
            createCell(row, i, columns[i], style);
        }
    }

    public void fillSheet(XSSFSheet sheet, List<ExcelReportResponse> data) {
        AtomicInteger rowCount = new AtomicInteger(1);
        XSSFSheet finalSheet = sheet;
        data.stream()
                .forEach(excelReportResponse -> {
                    Row row = finalSheet.createRow(rowCount.getAndIncrement());
                    int columnCount = 0;
                    createCell(row, columnCount++, excelReportResponse.getPatientName(), null);
                    createCell(row, columnCount++, excelReportResponse.getFeedback().replaceAll("^\"|\"$", ""), null);
                    createCell(row, columnCount++, excelReportResponse.getOptionalFeedback(), null);
                    createCell(row, columnCount++, excelReportResponse.getClinicName(), null);
                    createCell(row, columnCount, excelReportResponse.getCreatedAt(), null);
                });

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        if (value instanceof BigInteger) {
            cell.setCellValue(((BigInteger) value).longValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        if (style != null)
            cell.setCellStyle(style);
    }

    private void setColumnsNames() {
        columns = new String[]{"Patient Name ", "Feedback", "Optional Feedback ", "Location", "Created Date"};
    }
}
