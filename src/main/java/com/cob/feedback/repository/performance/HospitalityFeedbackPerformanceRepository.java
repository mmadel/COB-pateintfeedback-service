package com.cob.feedback.repository.performance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HospitalityFeedbackPerformanceRepository extends PerformanceRepository {
    @Query(value = "select count(id) from patient_feedback a " +
            "where a.created_at>= :startDate " +
            "and a.created_at<= :endDate " +
            "and a.clinic_id=  :clinicId " +
            "and JSON_EXTRACT(feedback_questions, '$.hospitalityFeedback')= :feedback", nativeQuery = true)
    Integer count(Long startDate, Long endDate, Long clinicId, String feedback);

    @Query(value = "select patient_name,JSON_EXTRACT(feedback_questions, '$.hospitalityFeedback'),optional_feedback , created_at , clinic_id    from patient_feedback a  " +
            "where a.created_at>= :startDate " +
            "and a.created_at<= :endDate " +
            "and a.clinic_id IN (:clinicIds) " +
            "and JSON_EXTRACT(feedback_questions, '$.hospitalityFeedback') IN :feedback", nativeQuery = true)
    List<Object> find(Long startDate, Long endDate,@Param("clinicIds") List<Long> clinicIds, List<String> feedback);
}
