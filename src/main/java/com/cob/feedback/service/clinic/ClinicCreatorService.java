package com.cob.feedback.service.clinic;

import com.cob.feedback.entity.ClinicEntity;
import com.cob.feedback.model.admin.Clinic;
import com.cob.feedback.repository.ClinicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ClinicCreatorService {
    @Autowired
    ClinicRepository repository;
    @Autowired
    ModelMapper mapper;


    public Clinic create(Clinic model) {
        ClinicEntity createdEntity = repository.save(mapper.map(model, ClinicEntity.class));
        model.setId(createdEntity.getId());
        return model;
    }
}
