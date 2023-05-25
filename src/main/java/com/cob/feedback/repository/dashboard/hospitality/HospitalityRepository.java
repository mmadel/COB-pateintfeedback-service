package com.cob.feedback.repository.dashboard.hospitality;

import com.cob.feedback.entity.FeedbackEntity;
import org.springframework.data.repository.CrudRepository;

public interface HospitalityRepository extends CrudRepository<FeedbackEntity, Long> {
}
