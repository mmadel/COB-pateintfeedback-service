package com.cob.feedback.service;

import com.cob.feedback.entity.ClinicEntity;
import com.cob.feedback.entity.FeedbackEntity;
import com.cob.feedback.model.Feedback;
import com.cob.feedback.repository.ClinicRepository;
import com.cob.feedback.repository.FeedbackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FeedbackCreatorService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    FeedbackRepository repository;
    @Autowired
    ClinicRepository clinicRepository;

    public Feedback create(Feedback model){
        ClinicEntity clinicEntity = clinicRepository.findById(model.getClinicId())
                .orElseThrow(() -> new IllegalArgumentException("Error Find Clinic"));
        FeedbackEntity feedbackEntity = mapper.map(model , FeedbackEntity.class);
        feedbackEntity.setClinicId(clinicEntity);
        repository.save(feedbackEntity);
        return model;
    }
}
