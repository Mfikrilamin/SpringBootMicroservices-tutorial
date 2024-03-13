package com.springbootmicroservices.Userservice.service;

import com.springbootmicroservices.Userservice.dto.SignUpRequest;

public interface UserService {
    public String signUpUser(SignUpRequest signUpRequest);
}
