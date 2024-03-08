package com.springbootmicroservices.Advertisementservice.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class UserContextFilter implements Filter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    // Intercept incoming request and set correlation id and auth token in
    // UserContext
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        UserContextHolder.getContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setAccessToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));

        LOGGER.info(
                "Management Service | UserContextFilter | doFilter | User Service Incoming Correlation id: {} | Auth Token : {}",
                UserContextHolder.getContext().getCorrelationId(),
                UserContextHolder.getContext().getAccessToken());

        filterChain.doFilter(httpServletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
