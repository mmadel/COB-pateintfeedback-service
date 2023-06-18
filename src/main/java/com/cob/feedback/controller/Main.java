package com.cob.feedback.controller;


import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        /*SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");//dd/MM/yyyy
        System.out.println(TimeZone.getTimeZone("Africa/Cairo"));
        sdfDate.setTimeZone(TimeZone.getTimeZone("Africa/Cairo"));
        Date now = new Date(1685610000000L);
        String strDate = sdfDate.format(now);
        System.out.println(strDate);*/
        /*Calendar c = Calendar.getInstance();
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int day = 1; day < monthMaxDays; day++) {
            System.out.println(day);
        }
        System.out.println(monthMaxDays);*/

       /* Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(1686581120585L);
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
        System.out.println(cal.get(Calendar.MONTH)+1);
        System.out.println(cal.get(Calendar.YEAR));*/
       /* TimeZone obj = TimeZone.getDefault();
        System.out.println("Default timezone object: " + obj);
                // getting the default name which is readable by the user
                String name = obj.getDisplayName();
        System.out.println("Display name of this timezone: " + name);*/
        Calendar c = Calendar.getInstance();
        System.out.println(c.getActualMaximum(Calendar.DAY_OF_MONTH));
        int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

}
