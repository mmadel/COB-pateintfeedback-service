package com.cob.feedback.service.user;

import com.cob.feedback.entity.ClinicEntity;
import com.cob.feedback.entity.UserEntity;
import com.cob.feedback.model.admin.user.UserModel;
import com.cob.feedback.repository.ClinicRepository;
import com.cob.feedback.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserCreatorService {
    @Autowired
    UserRepository repository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    ModelMapper mapper;

    @Autowired
    PasswordEncoder encoder;

    public UserModel create(UserModel model) {
        UserEntity toBeSaved = mapper.map(model, UserEntity.class);
        Set<ClinicEntity> clinics = null;
        if(model.getClinics() != null)
            clinics = model.getClinics().stream().map(clinicModel -> clinicRepository.findById(clinicModel.getId()).get()).collect(Collectors.toSet());
        toBeSaved.setClinics(clinics);
        //https://www.dariawan.com/tutorials/spring/illegalargumentexception-there-no-passwordencoder-mapped-id-null/
        toBeSaved.setPassword("{bcrypt}" + encoder.encode(model.getPassword()));
        UserEntity createdUser = repository.save(toBeSaved);
        model.setCreatedAt(createdUser.getCreatedAt());
        model.setId(createdUser.getId());
        return model;
    }
}
