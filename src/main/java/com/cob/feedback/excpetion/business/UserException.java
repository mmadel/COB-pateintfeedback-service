package com.cob.feedback.excpetion.business;

import org.springframework.http.HttpStatus;

public class UserException extends FeedbackException{
    public static final String USER_IS_EXISTS = Category.Business.value() + getPrefix() +"_00";
    public UserException(String code) {
        super(code);
    }

    public UserException(String code, Object[] parameters) {
        super(code, parameters);
    }

    public UserException(HttpStatus status, String code, Object[] parameters) {
        super(status, code, parameters);
    }

    protected static String getPrefix() {
        return "_user";
    }
}
