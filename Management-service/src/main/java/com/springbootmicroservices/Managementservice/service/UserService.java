package com.springbootmicroservices.Managementservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springbootmicroservices.Managementservice.model.Advertisement;

public interface UserService {
    List<Advertisement> getAllAdvertisements();

    ResponseEntity<Advertisement> getAdvertisementById(String advertisementId);
}
