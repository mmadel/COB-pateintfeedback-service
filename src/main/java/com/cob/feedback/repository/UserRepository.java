package com.cob.feedback.repository;

import com.cob.feedback.entity.ClinicEntity;
import com.cob.feedback.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> getByName(String name);
    @Query("Select u from UserEntity u where :clinic member  u.clinics" )
    UserEntity findByClinic(@Param("clinic") ClinicEntity clinic);
}
