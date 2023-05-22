package com.cob.feedback.repository;

import com.cob.feedback.entity.ClinicEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClinicRepository extends PagingAndSortingRepository<ClinicEntity, Long> {
}
