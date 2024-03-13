package com.springbootmicroservices.Userservice.convertor;

import org.springframework.stereotype.Component;

import com.springbootmicroservices.Userservice.dto.SignUpRequest;
import com.springbootmicroservices.Userservice.model.User;

@Component
public class UserMapper {

    public static User signUpRequestToUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getEmail());
        user.setSurname(signUpRequest.getSurname());
        user.setRole(signUpRequest.getRole());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());

        return user;
    }
}
