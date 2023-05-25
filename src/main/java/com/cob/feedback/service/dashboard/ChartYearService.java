package com.cob.feedback.service.dashboard;

import com.cob.feedback.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChartYearService {
    @Autowired
    DashboardService dashboardService;
    Map<String,Integer> values = new HashMap();
    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] monthNames = dfs.getMonths();

    public Integer get(Long startDate , Long endDate){
        List<Feedback> models = dashboardService.findByDateRange(startDate , endDate);
        fillYearContainer();
        models.forEach(feedback -> {
            String monthName = getMonthName(feedback.getCreatedAt());
            values.put(monthName, values.get(monthName) +1);

        });
        return null;
    }

    private String getMonthName(Long date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(date));
        return new SimpleDateFormat("MMMM").format(cal.getTime());
    }

    private void fillYearContainer(){
        for (String monthName : monthNames) {
            values.put(monthName,0);
        }
    }
}
