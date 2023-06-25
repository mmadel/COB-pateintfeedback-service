package com.cob.feedback.repository;

import com.cob.feedback.BeanFactory;
import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.repository.performance.ClinicalFeedbackPerformanceRepository;
import com.cob.feedback.repository.performance.HospitalityFeedbackPerformanceRepository;
import com.cob.feedback.repository.performance.PerformanceRepository;

public class ServiceFeedbackRepositoryBuilder {

    public static PerformanceRepository build(ServiceName serviceName){
        PerformanceRepository performanceRepository = null;
        switch (serviceName) {
            case HOSPITALITY:
                performanceRepository = BeanFactory.getBean(HospitalityFeedbackPerformanceRepository.class);
                break;
            case CLINICAL:
                performanceRepository = BeanFactory.getBean(ClinicalFeedbackPerformanceRepository.class);
                break;
        }
        return performanceRepository;
    }
}
