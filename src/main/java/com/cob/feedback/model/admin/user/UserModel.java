package com.cob.feedback.model.admin.user;

import com.cob.feedback.enums.UserRole;
import com.cob.feedback.model.admin.Clinic;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Setter
@Getter
public class UserModel {
    private Long id;

    private String name;
    private String password;

    private String address;


    private UserRole userRole;
    private List<Clinic> clinics;
    @Column
    private Long createdAt;
}
