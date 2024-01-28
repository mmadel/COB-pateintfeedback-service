package com.cob.feedback.model.reports;

import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.model.admin.Clinic;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExcelReportCriteria {
    long startDate;
    long endDate;
    Clinic clinic;
    List<Clinic> clinics;
    List<ServiceName> serviceName;
    List<String> feedbackFilter;
    String timeZone;
}
