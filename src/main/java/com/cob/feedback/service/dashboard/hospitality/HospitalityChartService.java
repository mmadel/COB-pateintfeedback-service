package com.cob.feedback.service.dashboard.hospitality;

import com.cob.feedback.entity.FeedbackEntity;
import com.cob.feedback.repository.dashboard.hospitality.HospitalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalityChartService {
    @Autowired
    HospitalityRepository repository;

    public List<FeedbackEntity> test(String name){
        //return repository.find(name);
        return null;
    }
}
