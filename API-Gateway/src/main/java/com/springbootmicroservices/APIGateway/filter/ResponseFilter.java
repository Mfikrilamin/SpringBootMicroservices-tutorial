package com.springbootmicroservices.APIGateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;

import reactor.core.publisher.Mono;

public class ResponseFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    FilterUtils filterUtils;

    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String correlationId = filterUtils.getCorrelationId(requestHeaders);
                LOGGER.info(
                        "API Gateway | ResponseFilter | postGlobalFilter | Adding the correlation id to the outbound headers. {}",
                        correlationId);
                exchange.getResponse().getHeaders().add(filterUtils.CORRELATION_ID, correlationId);
                LOGGER.info("API Gateway | ResponseFilter | postGlobalFilter | Completing outgoing request for {}.",
                        exchange.getRequest().getURI());
            }));
        };
    }

}

// public class ResponseFilter implements GlobalFilter {
// private final Logger LOGGER = LoggerFactory.getLogger(getClass());
// @Autowired
// FilterUtils filterUtils;

// @Override
// public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain
// chain) {
// return chain.filter(exchange).then(Mono.fromRunnable(new Runnable() {
// @Override
// public void run() {
// HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
// String correlationId = filterUtils.getCorrelationId(requestHeaders);
// LOGGER.info(
// "API Gateway | ResponseFilter | postGlobalFilter | Adding the correlation id
// to the outbound headers. {}",
// correlationId);
// exchange.getResponse().getHeaders().add(filterUtils.CORRELATION_ID,
// correlationId);
// LOGGER.info("API Gateway | ResponseFilter | postGlobalFilter | Completing
// outgoing request for {}.",
// exchange.getRequest().getURI());
// }
// }));
// }

// }
