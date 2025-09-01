package com.cob.feedback.controller;


import com.cob.feedback.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        long[] result = new long[3];
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(1703697618061L);
        result[0] = cal.get(Calendar.DAY_OF_MONTH);
        result[1] = cal.get(Calendar.MONTH) + 1;
        result[2] = cal.get(Calendar.HOUR_OF_DAY) + 1;
        System.out.println(result[0] + " , " + result[1] + " , " + result[2]);
    }

}
