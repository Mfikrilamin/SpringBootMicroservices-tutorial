package com.springbootmicroservices.Advertisementservice.service;

import java.util.List;

import com.springbootmicroservices.Advertisementservice.dto.AdvertisementRequest;
import com.springbootmicroservices.Advertisementservice.model.Advertisement;

public interface AdminService {
    void createAdvertisement(AdvertisementRequest advertisementRequest, String userId);

    void updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId);

    void deleteAdvertisement(String advertisementId);

    Advertisement approveAdvertisement(String advertisementId);

    Advertisement rejectAdvertisement(String advertisementId);

    List<Advertisement> getAllAdvertisementsForAdmin();

    Advertisement getAdvertisementByIdForAdmin(String advertisementId);
}
