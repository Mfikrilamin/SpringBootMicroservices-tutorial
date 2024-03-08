package com.springbootmicroservices.APIGateway.filter;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class TrackingFilter implements GlobalFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    FilterUtils filterUtils;

    // This filter responsible to track each request based on the correlationID
    // If the correlationID is not present, it will generate a new one
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeader = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeader)) {
            LOGGER.info("API Gateway | TrackingFilter | filter | correlation_id found in trackingfilter: {}",
                    filterUtils.getCorrelationId(requestHeader));
        } else {
            String UUID = generateCorrelationId();
            exchange = filterUtils.setCorrelationId(exchange, UUID);
            LOGGER.info("API Gateway | TrackingFilter | filter | correlation_id found in trackingfilter: {}",
                    filterUtils.getCorrelationId(requestHeader));
        }

        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders header) {
        return filterUtils.getCorrelationId(header) != null;
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

}
