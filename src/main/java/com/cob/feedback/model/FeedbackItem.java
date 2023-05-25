package com.cob.feedback.model;

import com.cob.feedback.enums.FeedbackFeeling;
import com.cob.feedback.enums.FeedbackQuestion;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class FeedbackItem implements Serializable {
    private FeedbackQuestion feedbackQuestion;
    private FeedbackFeeling feedbackFeeling;
}