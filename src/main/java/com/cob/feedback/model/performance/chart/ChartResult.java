package com.cob.feedback.model.performance.chart;

import com.cob.feedback.model.FeedbackQuestion;
import com.cob.feedback.utils.TimeUtils;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChartResult {
    Long time;
    FeedbackQuestion feedbackValue;

    public long getDay() {
        return TimeUtils.getDayInMilliSeconds(time)[0];
    }


}
