package com.cob.feedback.repository.dashboard.clinical;

import com.cob.feedback.entity.FeedbackEntity;
import com.cob.feedback.enums.FeedbackFeeling;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ClinicalRepository extends CrudRepository<FeedbackEntity, Long> {

    @Query(value = "select count(id) from patient_feedback a " +
            "where a.created_at>= :startDate " +
            "and a.created_at<= :endDate " +
            "and a.clinic_id= :clinicId " +
            "and JSON_EXTRACT(feedback_questions, '$.clinicalFeedback')= :feeling", nativeQuery = true)
    Integer countByFeelingAndDateRange(Long startDate,
                                   Long endDate,
                                   Long clinicId,
                                   String feeling);
}
