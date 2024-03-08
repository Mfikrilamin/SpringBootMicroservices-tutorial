package com.springbootmicroservices.Advertisementservice.convertor;

import org.springframework.stereotype.Component;

import com.springbootmicroservices.Advertisementservice.dto.AdvertisementRequest;
import com.springbootmicroservices.Advertisementservice.model.Advertisement;

@Component
public class AdvertismentMapper {
    public static Advertisement advertisementRequestToAdvertisement(AdvertisementRequest advertisementRequest) {
        Advertisement advertisement = new Advertisement();
        advertisement.setTitle(advertisementRequest.getTitle());
        advertisement.setPrice(advertisementRequest.getPrice());
        return advertisement;
    }
}
