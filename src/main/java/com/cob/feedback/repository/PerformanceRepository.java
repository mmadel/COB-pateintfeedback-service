package com.cob.feedback.repository;

import com.cob.feedback.entity.FeedbackEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface PerformanceRepository extends PagingAndSortingRepository<FeedbackEntity, Long> {
    Integer count(Long startDate, Long endDate, Long clinicId, String feedback);
}
