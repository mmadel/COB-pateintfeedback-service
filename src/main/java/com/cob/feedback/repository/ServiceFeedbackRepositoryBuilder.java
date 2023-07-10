package com.cob.feedback.repository;

import com.cob.feedback.BeanFactory;
import com.cob.feedback.enums.ServiceName;
import com.cob.feedback.repository.performance.ClinicalFeedbackPerformanceRepository;
import com.cob.feedback.repository.performance.HospitalityFeedbackPerformanceRepository;
import com.cob.feedback.repository.performance.PerformanceRepository;

import java.util.ArrayList;
import java.util.List;

public class ServiceFeedbackRepositoryBuilder {

    public static PerformanceRepository build(ServiceName serviceName) {
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

    public static List<PerformanceRepository> build(List<ServiceName> serviceName) {
        List<PerformanceRepository> repositories = new ArrayList<>();
        serviceName.forEach(service -> {
            if (service.equals(ServiceName.HOSPITALITY))
                repositories.add(build(ServiceName.HOSPITALITY));
            if (service.equals(ServiceName.CLINICAL))
                repositories.add(build(ServiceName.CLINICAL));
        });
        return repositories;
    }
}
