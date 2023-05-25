package com.cob.feedback.repository;

import com.cob.feedback.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DashboardRepository extends PagingAndSortingRepository<FeedbackEntity, Long> {
    @Query("SELECT fb FROM FeedbackEntity fb WHERE"+
            "((:dateFrom is null or fb.createdAt >= :dateFrom) AND (:dateTo is null or fb.createdAt < :dateTo) )" +
            "AND fb.clinicId.id = :clinicId ")
    List<FeedbackEntity> findByDay(
              @Param("dateFrom") Long dateFrom
            , @Param("dateTo") Long dateTo
            , @Param("clinicId") Long clinicId);
}
