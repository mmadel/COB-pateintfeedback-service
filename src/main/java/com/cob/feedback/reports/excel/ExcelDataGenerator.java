package com.cob.feedback.reports.excel;

import com.cob.feedback.model.reports.ExcelReportResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.math.BigInteger;
import java.util.List;

@Setter
@Getter
public class ExcelDataGenerator {

    private static XSSFSheet sheet = null;

    public static void createDataLines(XSSFWorkbook workbook, List<ExcelReportResponse> data) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ExcelReportResponse response : data) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, response.getPatientName(), style);
            createCell(row, columnCount++, response.getFeedback().replaceAll("^\"|\"$", ""), style);
            createCell(row, columnCount++, response.getOptionalFeedback(), style);
            createCell(row, columnCount, response.getCreatedAt(), style);
        }
        sheet = null;
    }

    public static void createHeaderLine(XSSFWorkbook workbook, String[] columns , String sheetName) {
        if (sheet == null)
            sheet = workbook.createSheet(sheetName);
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        for (int i = 0; i < columns.length; i++) {
            createCell(row, i, columns[i], style);
        }
    }

    private static void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof BigInteger) {
            cell.setCellValue(((BigInteger) value).longValue());
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

}
