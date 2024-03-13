package com.springbootmicroservices.Userservice.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootmicroservices.Userservice.convertor.UserMapper;
import com.springbootmicroservices.Userservice.dto.KeyClockUser;
import com.springbootmicroservices.Userservice.dto.SignUpRequest;
import com.springbootmicroservices.Userservice.model.User;
import com.springbootmicroservices.Userservice.repository.UserRepository;
import com.springbootmicroservices.Userservice.service.KeyCloakService;
import com.springbootmicroservices.Userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;
    private final KeyCloakService keyCloakService;

    @Override
    public String signUpUser(SignUpRequest signUpRequest) {

        LOGGER.info("UserServiceImpl | signUpUser is started");

        KeyClockUser keyClockUser = new KeyClockUser();
        keyClockUser.setFirstName(signUpRequest.getName());
        keyClockUser.setLastName(signUpRequest.getSurname());
        keyClockUser.setEmail(signUpRequest.getEmail());
        keyClockUser.setPassword(signUpRequest.getPassword());
        keyClockUser.setRole(signUpRequest.getRole());
        keyClockUser.setUsername(signUpRequest.getUsername());

        int status = keyCloakService.createUserWithKeycloak(keyClockUser);

        if (status == 201) {
            LOGGER.info("UserServiceImpl | signUpUser | status : " + status);

            User signUpUser = UserMapper.signUpRequestToUser(signUpRequest);

            signUpUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(signUpUser);

            return "Sign Up completed";
        }

        return "Not Register";
    }

}
