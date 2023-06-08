package com.cob.feedback.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Feedback {
    private Long id;
    private Long clinicId;

    private FeedbackQuestion feedbackQuestions;
    private String patientName;

    private String optionalFeedback;

    private long createdAt;
}