package com.cob.feedback.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class FeedbackItem implements Serializable {
    private String name;
    private Boolean itemValue;
}