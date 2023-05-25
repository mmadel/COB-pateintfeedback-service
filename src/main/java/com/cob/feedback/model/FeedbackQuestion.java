package com.cob.feedback.model;

import com.cob.feedback.enums.FeedbackFeeling;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedbackQuestion {
    private FeedbackFeeling hospitalityFeedback;
    private FeedbackFeeling clinicalFeedback;
}
