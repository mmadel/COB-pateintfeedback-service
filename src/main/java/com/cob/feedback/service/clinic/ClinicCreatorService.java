package com.cob.feedback.service.clinic;

import com.cob.feedback.entity.ClinicEntity;
import com.cob.feedback.entity.UserEntity;
import com.cob.feedback.excpetion.business.ClinicException;
import com.cob.feedback.excpetion.business.ReportingPerformanceException;
import com.cob.feedback.model.admin.Clinic;
import com.cob.feedback.repository.ClinicRepository;
import com.cob.feedback.repository.UserRepository;
import com.cob.feedback.utils.DateFormatter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
@Transactional
public class ClinicCreatorService {
    @Autowired
    ClinicRepository repository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper mapper;


    public Clinic create(Clinic model) {
        ClinicEntity createdEntity = repository.save(mapper.map(model, ClinicEntity.class));
        model.setId(createdEntity.getId());
        return model;
    }

    public void delete(long id) throws ClinicException {
        ClinicEntity tobeDeleted = repository.findById(id).get();
        List<UserEntity> users = userRepository.findByClinic(tobeDeleted);
        if(!users.isEmpty())
            throw new ClinicException(HttpStatus.INTERNAL_SERVER_ERROR, ClinicException.CLINIC_ASSIGN_TO_USER,
                    new Object[]{id});
        repository.delete(tobeDeleted);

    }
}
