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
        Date date = new Date(1703105809981L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String newYorkTime = sdf.format(date);
        System.out.println(newYorkTime);
    }

}
