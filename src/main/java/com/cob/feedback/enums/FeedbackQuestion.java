package com.cob.feedback.enums;

public enum FeedbackQuestion {
    Hospitality ("hospitality"),
    Clinical ("clinical");

    public final String label;

    FeedbackQuestion(String label) {
        this.label = label;
    }
}
