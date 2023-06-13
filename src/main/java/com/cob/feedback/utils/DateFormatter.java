package com.cob.feedback.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatter {

    public static String formatTimeStampAsString(long date) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date(date);
        return sdfDate.format(now);
    }
}
