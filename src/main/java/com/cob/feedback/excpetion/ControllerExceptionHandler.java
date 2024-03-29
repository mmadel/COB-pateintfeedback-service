package com.cob.feedback.excpetion;

import com.cob.feedback.excpetion.business.ClinicException;
import com.cob.feedback.excpetion.business.FeedbackException;
import com.cob.feedback.excpetion.business.ReportingPerformanceException;
import com.cob.feedback.excpetion.business.UserException;
import com.cob.feedback.excpetion.response.ControllerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler  {
    @Autowired
    ResourceBundleMessageSource messageSource;
    @ExceptionHandler(value = {FeedbackException.class})
    public ResponseEntity  handleFeedbackExceptionException(FeedbackException ex, WebRequest request) {
        String errorMessage = messageSource.getMessage(ex.getCode(), ex.getParameters(), Locale.ENGLISH);
                ControllerErrorResponse controllerErrorResponse = new ControllerErrorResponse(errorMessage, ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getStatus());
        log.error(controllerErrorResponse.getError());
        return new ResponseEntity(controllerErrorResponse, ex.getStatus() == null ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getStatus());
    }




}
