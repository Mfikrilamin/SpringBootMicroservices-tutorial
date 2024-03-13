package com.springbootmicroservices.Reportservice.service;

import com.springbootmicroservices.Reportservice.dto.AdvertisementDto;

public interface MessageService {
    void receiveMessage(AdvertisementDto advertisementDto);
}
