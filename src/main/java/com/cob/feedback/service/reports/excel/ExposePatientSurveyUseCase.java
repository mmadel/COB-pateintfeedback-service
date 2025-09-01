package com.cob.feedback.service.reports.excel;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.excpetion.business.ReportingPerformanceException;
import com.cob.feedback.model.reports.ExcelReportCriteria;
import com.cob.feedback.service.clinic.ClinicServiceFinder;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExposePatientSurveyUseCase {
    @Autowired
    ClinicServiceFinder finder;
    @Autowired
    ExcelReportService excelReportService;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final ZoneId UTC_ZONE = ZoneId.of("UTC");

    public XSSFWorkbook expose(String startDate, String endDate) throws ReportingPerformanceException, IOException {
        ExcelReportCriteria criteria = buildExcelReportCriteria(startDate, endDate);

        return excelReportService.export(criteria);
    }

    private ExcelReportCriteria buildExcelReportCriteria(String startDate, String endDate) throws ReportingPerformanceException {
        ExcelReportCriteria criteria = new ExcelReportCriteria();
        fillClinics(criteria);
        fillServiceNames(criteria);
        fillFeedbacks(criteria);
        fillDates(criteria, startDate, endDate);
        return criteria;
    }

    private void fillClinics(ExcelReportCriteria criteria) {
        criteria.setClinics(finder.find());
    }

    private void fillServiceNames(ExcelReportCriteria criteria) {
        List<ServiceName> serviceNames = new ArrayList<>();
        serviceNames.add(ServiceName.CLINICAL);
        serviceNames.add(ServiceName.HOSPITALITY);
        criteria.setServiceName(serviceNames);
    }

    private void fillFeedbacks(ExcelReportCriteria criteria) {
        List<String> feedBacks = new ArrayList<>();
        feedBacks.add("VGood");
        feedBacks.add("Good");
        feedBacks.add("Sad");
        criteria.setFeedbackFilter(feedBacks);
    }

    private void fillDates(ExcelReportCriteria criteria, String startDate, String endDate) throws ReportingPerformanceException {
        criteria.setStartDate(convertRange(startDate, endDate)[0]);
        criteria.setEndDate(convertRange(startDate, endDate)[1]);
        criteria.setTimeZone(UTC_ZONE.toString());
    }

    public long[] convertRange(String startDate, String endDate) throws ReportingPerformanceException {
        long start = convertToMillis(startDate, "Start Date");
        long end = convertToMillis(endDate, "End Date");
        long[] result = new long[2];
        result[0] = start;
        result[1] = end;
        if (start > end) {
            throw new ReportingPerformanceException(HttpStatus.CONFLICT, ReportingPerformanceException.DATA_RANGE_IS_INCORRECT,
                    new Object[]{startDate, endDate});
        }
        return result;
    }

    public long convertToMillis(String date, String boundaryValue) throws ReportingPerformanceException {
        try {
            LocalDate localDate = LocalDate.parse(date, FORMATTER);
            return localDate.atStartOfDay(UTC_ZONE).toInstant().toEpochMilli();
        } catch (DateTimeParseException e) {
            throw new ReportingPerformanceException(HttpStatus.CONFLICT, ReportingPerformanceException.DATA_IS_INCORRECT,
                    new Object[]{date, boundaryValue});
        }
    }
}
