package com.springbootmicroservices.Reportservice.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AdvertisementDto implements Serializable {

    private Long id;
    private String title;
    private Long viewCount;
}
