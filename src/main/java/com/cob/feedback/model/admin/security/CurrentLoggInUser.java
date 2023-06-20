package com.cob.feedback.model.admin.security;

import com.cob.salesforce.enums.admin.UserRole;
import com.cob.salesforce.models.admin.ClinicModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CurrentLoggInUser {
    private String name;
    private String address;
    private UserRole userRole;
    private List<ClinicModel> clinics;
}
