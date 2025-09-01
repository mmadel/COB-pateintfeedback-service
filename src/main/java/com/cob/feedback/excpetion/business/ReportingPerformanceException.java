package com.cob.feedback.excpetion.business;

import org.springframework.http.HttpStatus;

public class ReportingPerformanceException extends FeedbackException{
    public static final String EXPORT_DATA_IS_EMPTY = Category.Business.value() + getPrefix() +"_00";
    public static final String DATA_IS_INCORRECT = Category.Business.value() + getPrefix() +"_01";
    public static final String DATA_RANGE_IS_INCORRECT = Category.Business.value() + getPrefix() +"_02";
    public ReportingPerformanceException(String code) {
        super(code);
    }

    public ReportingPerformanceException(String code, Object[] parameters) {
        super(code, parameters);
    }

    public ReportingPerformanceException(HttpStatus status, String code, Object[] parameters) {
        super(status, code, parameters);
    }

    protected static String getPrefix() {
        return "_reporting";
    }
}
