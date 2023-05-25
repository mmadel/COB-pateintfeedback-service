package com.cob.feedback.repository.dashboard.hospitality;

import com.cob.feedback.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalityRepository extends JpaRepository<FeedbackEntity, Long> {

}
