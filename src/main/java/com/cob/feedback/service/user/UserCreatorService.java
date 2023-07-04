package com.cob.feedback.service.user;

import com.cob.feedback.entity.ClinicEntity;
import com.cob.feedback.entity.UserEntity;
import com.cob.feedback.excpetion.business.UserException;
import com.cob.feedback.model.admin.Clinic;
import com.cob.feedback.model.admin.user.UserModel;
import com.cob.feedback.repository.ClinicRepository;
import com.cob.feedback.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    public UserModel create(UserModel model) throws UserException {
        Optional<UserEntity> entity = repository.getByName(model.getName());
        if (entity.isPresent())
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_IS_EXISTS,
                    new Object[]{model.getName()});
        UserEntity toBeSaved = mapUserToEntity(model);

        toBeSaved.setPassword("{bcrypt}" + encoder.encode(model.getPassword()));
        UserEntity createdUser = repository.save(toBeSaved);
        model.setCreatedAt(createdUser.getCreatedAt());
        model.setId(createdUser.getId());
        return model;
    }

    public void delete(long id) {
        UserEntity tobeDeleted = repository.findById(id).get();
        tobeDeleted.setClinics(null);
        repository.delete(tobeDeleted);
    }

    public UserModel update(UserModel model) throws UserException {

        Optional<UserEntity> entity = repository.getByName(model.getName());

        UserEntity toBeUpdate = mapUserToEntity(model);
        if (entity.isPresent() && !model.getName().equals(entity.get().getName()))
            throw new UserException(HttpStatus.CONFLICT, UserException.USER_IS_EXISTS,
                    new Object[]{model.getName()});
        if (toBeUpdate.getPassword().equals(model.getPassword()))
            toBeUpdate.setPassword(model.getPassword());
        else
            toBeUpdate.setPassword("{bcrypt}" + encoder.encode(model.getPassword()));
        toBeUpdate.setCreatedAt(new Date().getTime());
        UserEntity updatedUser = repository.save(toBeUpdate);
        model.setCreatedAt(updatedUser.getCreatedAt());
        return model;
    }

    private UserEntity mapUserToEntity(UserModel model) {
        //https://www.dariawan.com/tutorials/spring/illegalargumentexception-there-no-passwordencoder-mapped-id-null/
        UserEntity entity = mapper.map(model, UserEntity.class);
        entity.setClinics(getClinics(model.getClinics()));
        return entity;
    }

    private Set<ClinicEntity> getClinics(List<Clinic> clinicsModels) {
        Set<ClinicEntity> clinics = null;
        if (clinicsModels != null)
            clinics = clinicsModels.stream().map(clinicModel -> clinicRepository.findById(clinicModel.getId()).get()).collect(Collectors.toSet());
        return clinics;
    }
}
