package com.cob.feedback.service.reports.excel;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.excpetion.business.ReportingPerformanceException;
import com.cob.feedback.model.reports.ExcelReportCriteria;
import com.cob.feedback.model.reports.ExcelReportResponse;
import com.cob.feedback.repository.ServiceFeedbackRepositoryBuilder;
import com.cob.feedback.repository.performance.ClinicalFeedbackPerformanceRepository;
import com.cob.feedback.repository.performance.HospitalityFeedbackPerformanceRepository;
import com.cob.feedback.utils.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExcelReportService {
    List<Object> searchResponse;
    Map<ServiceName, List<Object>> serviceData;
    ExcelReportCriteria criteria;

    public void search(ExcelReportCriteria criteria) {
        this.criteria = criteria;
        serviceData = new HashMap<>();
        ServiceFeedbackRepositoryBuilder.build(criteria.getServiceName())
                .forEach(performanceRepository -> {
                    if (performanceRepository instanceof HospitalityFeedbackPerformanceRepository)
                        serviceData.put(ServiceName.HOSPITALITY, performanceRepository.find(criteria.getStartDate(), criteria.getEndDate(), criteria.getClinicId(), criteria.getFeedbackFilter()));
                    if (performanceRepository instanceof ClinicalFeedbackPerformanceRepository)
                        serviceData.put(ServiceName.CLINICAL, performanceRepository.find(criteria.getStartDate(), criteria.getEndDate(), criteria.getClinicId(), criteria.getFeedbackFilter()));
                });

        //searchResponse = performanceRepository.find(criteria.getStartDate(), criteria.getEndDate(), criteria.getClinicId(), criteria.getFeedbackFilter());
    }

    public String[] getColumnsNames() {
        return new String[]{"Patient Name ", "Feedback", "Optional Feedback ", "Created Date"};
    }

    public List<ExcelReportResponse> getSingleServiceData() throws ReportingPerformanceException {
        if (searchResponse.size() == 0) {
            throw new ReportingPerformanceException(HttpStatus.CONFLICT, ReportingPerformanceException.EXPORT_DATA_IS_EMPTY,
                    new Object[]{
                            DateFormatter.formatTimeStampAsString(criteria.getStartDate()),
                            DateFormatter.formatTimeStampAsString(criteria.getEndDate()), criteria.getClinicId()});
        }
        List<ExcelReportResponse> excelReportResponses = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        for (Object o : searchResponse) {
            Object[] plainValues = (Object[]) o;
            excelReportResponses.add(ExcelReportResponse.builder()
                    .patientName((String) plainValues[0])
                    .feedback((String) plainValues[1])
                    .optionalFeedback((String) plainValues[2])
                    .createdAt(formatter.format(new Date(((BigInteger) plainValues[3]).longValue())))
                    .build());
        }
        return excelReportResponses;
    }

    public Map<ServiceName, List<ExcelReportResponse>> getMultipleData() throws ReportingPerformanceException {
        Map<ServiceName, List<ExcelReportResponse>> result = new HashMap<>();
        for (ServiceName serviceName : serviceData.keySet()) {
            searchResponse = serviceData.get(serviceName);
            result.put(serviceName, getSingleServiceData());
        }
        return result;
    }
}