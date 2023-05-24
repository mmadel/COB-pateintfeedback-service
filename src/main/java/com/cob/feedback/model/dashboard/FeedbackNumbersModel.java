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
    private int goodFeedbackTotal;
    private int vSadFeedbackTotal;
    private int sadFeedbackTotal;
}
