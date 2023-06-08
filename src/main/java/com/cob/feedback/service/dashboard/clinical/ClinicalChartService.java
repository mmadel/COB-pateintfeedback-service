package com.cob.feedback.service.dashboard.clinical;

import com.cob.feedback.enums.FeedbackFeeling;
import com.cob.feedback.repository.dashboard.clinical.ClinicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinicalChartService {

    @Autowired
    ClinicalRepository repository;

    public Integer countByFeelingAndDateRange(Long startDate,
                                              Long endDate,
                                              Long clinicId,
                                              String feeling) {

        return repository.countByFeelingAndDateRange(startDate, endDate, clinicId, feeling);
    }
}
