package com.cob.feedback.repository;

import com.cob.feedback.entity.FeedbackEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClinicalFeedbackRepository extends PagingAndSortingRepository<FeedbackEntity, Long> {
}
