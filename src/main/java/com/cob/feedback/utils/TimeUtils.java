package com.cob.feedback.utils;

import com.cob.feedback.enums.ChartTimeUnit;

import java.util.Arrays;
import java.util.Calendar;

public class TimeUtils {

    public static long[] getDayInMilliSeconds(long milliseconds) {
        long[] result = new long[3];
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milliseconds);
        result[0] = cal.get(Calendar.DAY_OF_MONTH);
        result[1] = cal.get(Calendar.MONTH) + 1;
        result[2] = cal.get(Calendar.HOUR_OF_DAY) + 1;

        return result;
    }

    public static Double[] getTimeScale(ChartTimeUnit chartTimeUnit) {
        Double[] scale = null;
        Calendar calendar = Calendar.getInstance();
        final int HOURS_PER_DAY = 24;
        final int MONTHS_PER_YEAR = 12;
        switch (chartTimeUnit) {
            case Day:
                scale = new Double[HOURS_PER_DAY];
                break;
            case Month:
                scale = new Double[calendar.getActualMaximum(Calendar.DAY_OF_MONTH)];
                break;
            case Year:
                scale = new Double[MONTHS_PER_YEAR];
                break;
        }
        Arrays.fill(scale, 0.0);
        return scale;
    }
}
