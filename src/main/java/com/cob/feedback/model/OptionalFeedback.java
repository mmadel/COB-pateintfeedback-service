package com.cob.feedback.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OptionalFeedback implements Serializable {
    private String feedback;
    private String patientName;
}