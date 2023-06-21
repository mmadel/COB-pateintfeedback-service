package com.cob.feedback.controller;

import com.cob.feedback.model.admin.security.LoginRequest;
import com.cob.feedback.model.admin.security.LoginResponse;
import com.cob.feedback.model.admin.user.UserModel;
import com.cob.feedback.service.security.JWTGeneratorService;
import com.cob.feedback.service.security.SecurityUser;
import com.cob.feedback.service.user.UserCreatorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("authentication")
public class AuthenticationController {
    @Autowired
    JWTGeneratorService jwtGeneratorService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ModelMapper mapper;
    @Autowired
    UserCreatorService creator;

    @PostMapping("/login")
    public ResponseEntity generateToken(@RequestBody LoginRequest model) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        model.getUserName(), model.getPassword()));
        return new ResponseEntity(LoginResponse.builder()
                .accessToken(jwtGeneratorService.generateToken(authentication))
                .userId(((SecurityUser) authentication.getPrincipal()).user.getId())
                .userRole(((SecurityUser) authentication.getPrincipal()).user.getUserRole().label)
                .build(), HttpStatus.OK);
    }
    @ResponseBody
    @PostMapping("/register")
    public ResponseEntity create(@RequestBody UserModel model) {
        return new ResponseEntity(creator.create(model), HttpStatus.OK);
    }
}
