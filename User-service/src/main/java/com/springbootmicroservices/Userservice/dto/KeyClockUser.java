package com.springbootmicroservices.Userservice.dto;

import lombok.Data;

@Data
public class KeyClockUser {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;
}
