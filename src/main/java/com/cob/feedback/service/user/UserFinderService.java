package com.cob.feedback.service.user;

import com.cob.feedback.entity.UserEntity;
import com.cob.feedback.model.admin.Clinic;
import com.cob.feedback.model.admin.user.UserModel;
import com.cob.feedback.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserFinderService {
    @Autowired
    UserRepository repository;
    @Autowired
    ModelMapper mapper;

    public List<UserModel> find() {
        return StreamSupport
                .stream(Spliterators
                        .spliteratorUnknownSize(repository.findAll().iterator(), 0), false)
                .map(entity -> mapUserModel(entity))
                .collect(Collectors.toList());
    }

    public UserModel findById(long id) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Id"));
        return mapper.map(userEntity, UserModel.class);
    }

    private UserModel mapUserModel(UserEntity entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setAddress(entity.getAddress());
        model.setUserRole(entity.getUserRole());
        model.setClinics(entity.getClinics().stream().map(clinicEntity -> mapper.map(clinicEntity, Clinic.class)).collect(Collectors.toList()));
        return model;
    }


}
