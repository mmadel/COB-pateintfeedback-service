package com.cob.feedback.service.dashboard.hospitality;

import com.cob.feedback.repository.dashboard.hospitality.HospitalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalityChartService {
    @Autowired
    HospitalityRepository repository;
}
