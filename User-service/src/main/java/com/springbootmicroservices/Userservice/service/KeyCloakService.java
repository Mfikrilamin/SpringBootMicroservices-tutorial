package com.springbootmicroservices.Userservice.service;

import com.springbootmicroservices.Userservice.dto.KeyClockUser;
import com.springbootmicroservices.Userservice.dto.LoginRequest;

public interface KeyCloakService {
    // public AccessTokenResponse loginWithKeycloak(LoginRequest loginRequest);
    public int createUserWithKeycloak(KeyClockUser keyClockUser);
}
