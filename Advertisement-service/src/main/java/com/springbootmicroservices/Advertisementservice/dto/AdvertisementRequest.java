package com.springbootmicroservices.Advertisementservice.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AdvertisementRequest {
    private String title;
    private BigDecimal price;
}
