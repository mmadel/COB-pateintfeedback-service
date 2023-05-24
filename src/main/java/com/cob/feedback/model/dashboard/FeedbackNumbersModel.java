package com.cob.feedback.model.dashboard;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class FeedbackNumbersModel {
    private int total;
    private int vGoodFeedbackTotal;
    private double vGoodFeedbackPercentage;
    private int goodFeedbackTotal;
    private double goodFeedbackPercentage;
    private int vSadFeedbackTotal;
    private double vSadFeedbackPercentage;
    private int sadFeedbackTotal;
    private double sadFeedbackPercentage;
}
