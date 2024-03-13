package com.springbootmicroservices.Userservice.utils;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class UserContext {
    public static final String CORRELATION_ID = "correlation_id";
    public static final String AUTH_TOKEN = "Authorization";

    private String correlationId;
    private String accessToken;
}
