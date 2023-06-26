package com.cob.feedback.model.admin.security;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginResponse {
    private String accessToken;
    private String userRole;
    private Long  userId;
}
