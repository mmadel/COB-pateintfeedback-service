package com.cob.feedback.repository;

import com.cob.feedback.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HospitalityFeedbackRepository extends PagingAndSortingRepository<FeedbackEntity, Long> {
    @Query(value = "select count(id) from patient_feedback a " +
            "where a.created_at>= :startDate " +
            "and a.created_at<= :endDate " +
            "and a.clinic_id= :clinicId " +
            "and JSON_EXTRACT(feedback_questions, '$.hospitalityFeedback')= :feedback", nativeQuery = true)
    Integer count(Long startDate, Long endDate, Long clinicId, String feedback);

    @Query(value = "select count(id) from patient_feedback a " +
            "where a.created_at>= :startDate " +
            "and a.created_at<= :endDate " +
            "and a.clinic_id= :clinicId ", nativeQuery = true)
    Integer getTotalNumber(Long startDate, Long endDate, Long clinicId);
}
