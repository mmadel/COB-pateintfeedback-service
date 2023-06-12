package com.cob.feedback.repository;

import org.springframework.data.jpa.repository.Query;

public interface ClinicalFeedbackRepository extends  PerformanceRepository {
    @Query(value = "select count(id) from patient_feedback a " +
            "where a.created_at>= :startDate " +
            "and a.created_at<= :endDate " +
            "and a.clinic_id= :clinicId " +
            "and JSON_EXTRACT(feedback_questions, '$.clinicalFeedback')= :feedback", nativeQuery = true)
    Integer count(Long startDate, Long endDate, Long clinicId, String feedback);
}
