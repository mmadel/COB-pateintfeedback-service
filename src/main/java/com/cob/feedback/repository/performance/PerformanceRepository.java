package com.cob.feedback.repository.performance;

import com.cob.feedback.entity.FeedbackEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface PerformanceRepository extends PagingAndSortingRepository<FeedbackEntity, Long> {
    Integer count(Long startDate, Long endDate, Long clinicId, String feedback);
    List<Object> find(Long startDate, Long endDate, Long clinicId, List<String> feedback);
}
