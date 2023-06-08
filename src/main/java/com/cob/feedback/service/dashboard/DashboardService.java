package com.cob.feedback.service.dashboard;

import com.cob.feedback.model.Feedback;
import com.cob.feedback.repository.DashboardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    DashboardRepository dashboardRepository;

    @Autowired
    ModelMapper mapper;

    public List<Feedback> findByDateRange(Long startDate, Long endDate) {
        return dashboardRepository.findByDay(startDate, endDate, 1L)
                .stream()
                .map(feedbackEntity -> mapper.map(feedbackEntity, Feedback.class))
                .collect(Collectors.toList());
    }

    /*public FeedbackNumbersModel calculateNumberOfFeedbackFeeling(Long startDate, Long endDate) {
        List<Feedback> models = findByDateRange(startDate, endDate);
        AtomicInteger vGood = new AtomicInteger();
        AtomicInteger good = new AtomicInteger();
        AtomicInteger vSad = new AtomicInteger();
        AtomicInteger sad = new AtomicInteger();
        models.forEach(feedback -> {
            if (feedback.getFeedbackValue().equals("VeryGood"))
                vGood.getAndIncrement();
            if (feedback.getFeedbackValue().equals("Happy"))
                good.getAndIncrement();
            if (feedback.getFeedbackValue().equals("Meh"))
                sad.getAndIncrement();
            if (feedback.getFeedbackValue().equals("Frown"))
                vSad.getAndIncrement();
        });
        return FeedbackNumbersModel.builder()
                .total(models.size())
                .vGoodFeedbackTotal(vGood.intValue())
                .vGoodFeedbackPercentage(PercentageCalculator.calculatePercentage(models.size(), vGood.intValue()))
                .goodFeedbackTotal(good.intValue())
                .goodFeedbackPercentage(PercentageCalculator.calculatePercentage(models.size(), good.intValue()))
                .vSadFeedbackTotal(vSad.intValue())
                .vSadFeedbackPercentage(PercentageCalculator.calculatePercentage(models.size(), vSad.intValue()))
                .sadFeedbackTotal(sad.intValue())
                .sadFeedbackPercentage(PercentageCalculator.calculatePercentage(models.size(), sad.intValue()))
                .build();
    }*/
}
