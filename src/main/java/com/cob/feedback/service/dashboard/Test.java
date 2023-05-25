package com.cob.feedback.service.dashboard;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] monthNames = dfs.getMonths();
        Date currentDate = new Date(1674597540000L);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        System.out.println(new SimpleDateFormat("MMMM").format(cal.getTime()));
    }
}
