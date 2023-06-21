package com.cob.feedback.model.admin.security;


import com.cob.feedback.enums.UserRole;
import com.cob.feedback.model.admin.Clinic;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CurrentLoggInUser {
    private String name;
    private String address;
    private UserRole userRole;
    private List<Clinic> clinics;
}
