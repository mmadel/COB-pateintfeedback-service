package com.cob.feedback.service.reports.excel;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.excpetion.business.ReportingPerformanceException;
import com.cob.feedback.model.admin.Clinic;
import com.cob.feedback.model.reports.ExcelReportCriteria;
import com.cob.feedback.model.reports.ExcelReportResponse;
import com.cob.feedback.reports.excel.ExcelGenerator;
import com.cob.feedback.repository.performance.ClinicalFeedbackPerformanceRepository;
import com.cob.feedback.repository.performance.HospitalityFeedbackPerformanceRepository;
import com.cob.feedback.service.clinic.ClinicServiceFinder;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExcelReportService {
    private String timeZone;
    private List<Clinic> clinics;
    @Autowired
    HospitalityFeedbackPerformanceRepository hospitalityFeedbackPerformanceRepository;
    @Autowired
    ClinicalFeedbackPerformanceRepository clinicalFeedbackPerformanceRepository;

    @Autowired
    ExcelGenerator excelGenerator;

    public XSSFWorkbook export(ExcelReportCriteria criteria) throws ReportingPerformanceException, IOException {
        timeZone = criteria.getTimeZone();
        clinics = criteria.getClinics();
        List<Long> clinicIds = getClinicsIds();
        Map<ServiceName, List<Object>> data = findData(criteria.getServiceName(), criteria.getStartDate(), criteria.getEndDate(), criteria.getFeedbackFilter(), clinicIds);
        Map<ServiceName, List<ExcelReportResponse>> result = constructData(data);
        return exportData(result);
    }

    private Map<ServiceName, List<Object>> findData(List<ServiceName> selectedServiceNames, Long startDate, Long endDate, List<String> feedbackFilter, List<Long> clinicIds) {
        Map<ServiceName, List<Object>> serviceData = new HashMap<>();
        selectedServiceNames.forEach(serviceName -> {
            switch (serviceName) {
                case HOSPITALITY:
                    serviceData.put(ServiceName.HOSPITALITY, hospitalityFeedbackPerformanceRepository.find(startDate, endDate, clinicIds, feedbackFilter));
                    break;
                case CLINICAL:
                    serviceData.put(ServiceName.CLINICAL, clinicalFeedbackPerformanceRepository.find(startDate, endDate, clinicIds, feedbackFilter));
                    break;
            }
        });
        return serviceData;
    }

    private Map<ServiceName, List<ExcelReportResponse>> constructData(Map<ServiceName, List<Object>> data) throws ReportingPerformanceException {

        Map<ServiceName, List<ExcelReportResponse>> result = new HashMap<>();
        for (ServiceName serviceName : data.keySet()) {
            List<Object> serviceNameReturnData = data.get(serviceName);
            if (serviceNameReturnData.size() == 0) {
                throw new ReportingPerformanceException(HttpStatus.CONFLICT, ReportingPerformanceException.EXPORT_DATA_IS_EMPTY,
                        new Object[]{});
            }
            List<ExcelReportResponse> excelReportResponses = new ArrayList<>();
            for (Object o : serviceNameReturnData) {
                Object[] plainValues = (Object[]) o;
                excelReportResponses.add(ExcelReportResponse.builder()
                        .patientName((String) plainValues[0])
                        .feedback((String) plainValues[1])
                        .optionalFeedback((String) plainValues[2])
                        .clinicName(getClinicName((BigInteger) plainValues[4]))
                        .createdAt(createDate(((BigInteger) plainValues[3]).longValue()))
                        .build());
            }
            result.put(serviceName, excelReportResponses);
        }
        return result;
    }

    private String getClinicName(BigInteger clinicId) {
        return clinics.stream()
                .filter(clinic -> clinic.getId().equals(clinicId.longValue()))
                .findFirst()
                .get().getName();

    }

    private List<Long> getClinicsIds() {
        return clinics.stream()
                .map(clinic -> clinic.getId())
                .collect(Collectors.toList());
    }

    private String createDate(Long dateInMillisecond) {
        Date date = new Date(dateInMillisecond);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf.format(date);

    }

    private XSSFWorkbook exportData(Map<ServiceName, List<ExcelReportResponse>> data) throws IOException {
        String[] serviceNames = data.keySet().stream()
                .map(ServiceName::name)
                .toArray(String[]::new);
        XSSFWorkbook workbook = createExcelDocument(serviceNames);
        data.forEach((serviceName, excelReportResponse) -> {
            String sheetName = serviceName.name().substring(0, 1).toUpperCase() + serviceName.name().substring(1).toLowerCase();
            excelGenerator.fillSheet(workbook.getSheet(sheetName), excelReportResponse);
        });
        return workbook;
    }

    private XSSFWorkbook createExcelDocument(String[] serviceNames) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (String sheetName : serviceNames) {
            excelGenerator.createSheet(workbook, sheetName);
        }
        return workbook;
    }

}