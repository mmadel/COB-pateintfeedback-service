package com.cob.feedback.repository.performance;

import com.cob.feedback.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PerformanceChartRepository extends JpaRepository<FeedbackEntity, Long> {

    @Query(value = " select fb.created_at,fb.feedback_questions from patient_feedback fb " +
            "where fb.created_at>= :startDate " +
            "and fb.created_at<= :endDate " +
            "and fb.clinic_id = :clinicId", nativeQuery = true)
    List get(Long startDate, Long endDate, Long clinicId);


}
