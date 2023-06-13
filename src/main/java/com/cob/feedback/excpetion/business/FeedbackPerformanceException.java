package com.cob.feedback.excpetion.business;

import org.springframework.http.HttpStatus;

public class FeedbackPerformanceException extends FeedbackException {
    public static final String PERFORMANCE_INDEX_IS_EMPTY = Category.Business.value() + getPrefix() +"_00";
    public FeedbackPerformanceException(String code) {
        super(code);
    }

    public FeedbackPerformanceException(String code, Object[] parameters) {
        super(code, parameters);
    }

    public FeedbackPerformanceException(HttpStatus status, String code, Object[] parameters) {
        super(status, code, parameters);
    }

    protected static String getPrefix() {
        return "_performance_index";
    }
}
