package com.cob.feedback.repository.dashboard.clinical;

import com.cob.feedback.entity.FeedbackEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClinicalRepository extends CrudRepository<FeedbackEntity, Long> {
}
