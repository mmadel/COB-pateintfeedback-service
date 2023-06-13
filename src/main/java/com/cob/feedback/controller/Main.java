package com.cob.feedback.controller;


import com.cob.feedback.formula.AverageFormula;
import com.cob.feedback.formula.HappyIndexFormula;
import com.cob.feedback.formula.NPSFormula;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");//dd/MM/yyyy
        System.out.println(TimeZone.getTimeZone("Africa/Cairo"));
        sdfDate.setTimeZone(TimeZone.getTimeZone("Africa/Cairo"));
        Date now = new Date(1685610000000L);
        String strDate = sdfDate.format(now);
        System.out.println(strDate);
       /* TimeZone obj = TimeZone.getDefault();
        System.out.println("Default timezone object: " + obj);
                // getting the default name which is readable by the user
                String name = obj.getDisplayName();
        System.out.println("Display name of this timezone: " + name);*/
    }

}
