package com.cob.feedback.service.clinic;

import com.cob.feedback.model.admin.Clinic;
import com.cob.feedback.repository.ClinicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicServiceFinder {

    @Autowired
    ClinicRepository repository;
    @Autowired
    ModelMapper mapper;
    public List<Clinic> findByUserId(Long userId) {
        return repository.findUserClinics(userId).stream()
                .map(clinicEntity -> mapper.map(clinicEntity, Clinic.class))
                .collect(Collectors.toList());
    }

    public List<Clinic> find(){
        List<Clinic> clinics = new ArrayList<>();
        repository.findAll().forEach(clinicEntity -> {
            clinics.add(mapper.map(clinicEntity, Clinic.class));
        });
        return clinics;
    }
}
