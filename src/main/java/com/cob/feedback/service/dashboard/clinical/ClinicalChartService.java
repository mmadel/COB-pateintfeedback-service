package com.cob.feedback.service.dashboard.clinical;

import com.cob.feedback.repository.dashboard.clinical.ClinicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicalChartService {

    @Autowired
    ClinicalRepository repository;
}
