package com.cob.feedback.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Feedback {
    private Long id;
    private Long clinicId;
    private List<FeedbackItem> items;
    private String patientName;

    private String optionalFeedback;

    private long createdAt;
}