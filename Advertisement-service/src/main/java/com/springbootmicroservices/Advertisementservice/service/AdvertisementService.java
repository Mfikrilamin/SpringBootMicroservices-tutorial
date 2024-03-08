package com.springbootmicroservices.Advertisementservice.service;

import java.util.List;

import com.springbootmicroservices.Advertisementservice.model.Advertisement;

public interface AdvertisementService {

    List<Advertisement> getAllAdvertisements();

    Advertisement getAdvertisement(String advertisementId);
}
