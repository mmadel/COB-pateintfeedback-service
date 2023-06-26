package com.cob.feedback.service.reports.excel;

import com.cob.feedback.excpetion.business.FeedbackPerformanceException;
import com.cob.feedback.excpetion.business.ReportingPerformanceException;
import com.cob.feedback.model.reports.ExcelReportCriteria;
import com.cob.feedback.model.reports.ExcelReportResponse;
import com.cob.feedback.repository.ServiceFeedbackRepositoryBuilder;
import com.cob.feedback.repository.performance.PerformanceRepository;
import com.cob.feedback.utils.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelReportService {
    List<Object> searchResponse;
    ExcelReportCriteria criteria;
    public void search(ExcelReportCriteria criteria) {
        this.criteria = criteria;
        PerformanceRepository performanceRepository = ServiceFeedbackRepositoryBuilder.build(criteria.getServiceName());
        searchResponse = performanceRepository.find(criteria.getStartDate(), criteria.getEndDate(), criteria.getClinicId(), criteria.getFeedbackFilter());
    }

    public String[] getColumnsNames() {
        return new String[]{"Patient Name ", "Feedback", "Optional Feedback ", "Created Date"};
    }

    public List<ExcelReportResponse> getData() throws ReportingPerformanceException {
        if(searchResponse.size() ==0){
            throw new ReportingPerformanceException(HttpStatus.CONFLICT, ReportingPerformanceException.EXPORT_DATA_IS_EMPTY,
                    new Object[]{
                             DateFormatter.formatTimeStampAsString(criteria.getStartDate()),
                            DateFormatter.formatTimeStampAsString(criteria.getEndDate()), criteria.getClinicId()});
        }
        List<ExcelReportResponse> excelReportResponses = new ArrayList<>();
        DateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        for (int i = 0; i < searchResponse.size(); i++) {
            Object[] plainValues = (Object[]) searchResponse.get(i);
            excelReportResponses.add(ExcelReportResponse.builder()
                    .patientName((String) plainValues[0])
                    .feedback((String) plainValues[1])
                    .optionalFeedback((String) plainValues[2])
                    .createdAt(formatter.format(new Date(((BigInteger) plainValues[3]).longValue())))
                    .build());
        }
        return excelReportResponses;
    }
}