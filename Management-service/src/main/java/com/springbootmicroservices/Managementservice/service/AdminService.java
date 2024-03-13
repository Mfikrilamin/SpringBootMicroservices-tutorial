package com.springbootmicroservices.Managementservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.springbootmicroservices.Managementservice.dto.AdvertisementRequest;
import com.springbootmicroservices.Managementservice.model.Advertisement;

public interface AdminService {
    ResponseEntity<?> createAdvertisement(AdvertisementRequest advertisementRequest, String userId);

    ResponseEntity<?> updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId);

    ResponseEntity<?> deleteAdvertisement(String advertisementId);

    List<Advertisement> getAllAdvertisements();

    ResponseEntity<Advertisement> getAdvertisementById(String advertisementId);

    ResponseEntity<?> approveAdvertisement(String advertisementId);

    ResponseEntity<?> rejectAdvertisement(String advertisementId);
}
