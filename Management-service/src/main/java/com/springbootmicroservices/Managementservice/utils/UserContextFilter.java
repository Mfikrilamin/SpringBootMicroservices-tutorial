package com.springbootmicroservices.Managementservice.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class UserContextFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        UserContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setAccessToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));

        LOGGER.info(
                "Management Service | UserContextFilter | doFilter | User Service Incoming Correlation id: {} | Auth Token : {}",
                UserContextHolder.getContext().getCorrelationId(),
                UserContextHolder.getContext().getAccessToken());

        chain.doFilter(httpServletRequest, response);
    }

}
