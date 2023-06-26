package com.cob.feedback.repository;

import com.cob.feedback.entity.ClinicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClinicRepository extends PagingAndSortingRepository<ClinicEntity, Long> {

    @Query("Select u.clinics from UserEntity u where u.id = :userId" )
    List<ClinicEntity> findUserClinics(@Param("userId") Long userId);
}
