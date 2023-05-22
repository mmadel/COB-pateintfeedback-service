package com.cob.feedback.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Feedback {
    private Long id;
    private Long clinicId;
    private OptionalFeedback optionalFeedback;
    private List<FeedbackItem> items;
    private String feedbackValue;
}