package com.cob.feedback.model.chart;

import com.cob.feedback.enums.ChartTimeUnit;
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

    public long getGroupedValue(ChartTimeUnit timeUnit) {
        switch (timeUnit){
            case Day:
                return TimeUtils.getDayInMilliSeconds(time)[2];
            case Month:
                return TimeUtils.getDayInMilliSeconds(time)[0];
            case Year:
                return TimeUtils.getDayInMilliSeconds(time)[1];
        }
        return 0;
    }


}
