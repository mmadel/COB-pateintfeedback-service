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
        //America/New_York
        //Africa/Cairo
        Date date = new Date(1701381600000L);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a");
        System.out.println(sdf.format(date));
        sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println(sdf.format(date));
    }

}
