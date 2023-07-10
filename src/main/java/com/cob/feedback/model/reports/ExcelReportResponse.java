package com.cob.feedback.model.reports;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ExcelReportResponse {
    String patientName;
    String feedback;
    String optionalFeedback;
    String createdAt;

    String clinicName;
}
