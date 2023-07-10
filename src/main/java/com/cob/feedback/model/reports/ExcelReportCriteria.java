package com.cob.feedback.model.reports;

import com.cob.feedback.enums.ServiceName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExcelReportCriteria {
    long startDate;
    long endDate;
    long clinicId;
    List<ServiceName> serviceName;
    List<String> feedbackFilter;
}
