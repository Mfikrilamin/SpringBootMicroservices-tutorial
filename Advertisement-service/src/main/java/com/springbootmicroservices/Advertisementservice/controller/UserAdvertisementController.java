package com.springbootmicroservices.Advertisementservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmicroservices.Advertisementservice.model.Advertisement;
import com.springbootmicroservices.Advertisementservice.service.AdvertisementService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user_role")
public class UserAdvertisementController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdvertisementService advertisementService;

    @GetMapping("/alladvertisements")
    public ResponseEntity<List<Advertisement>> getAllAdvertisement() {
        LOGGER.info("UserAdvertisementController | getAllAdvertisements is started");

        LOGGER.info("UserAdvertisementController | getAllAdvertisements size : "
                + advertisementService.getAllAdvertisements().size());

        return ResponseEntity.status(HttpStatus.OK).body(advertisementService.getAllAdvertisements());
    }

    @GetMapping("/advertisements/{advertisementId}")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable String advertisementId) {
        LOGGER.info("UserAdvertisementController | getAdvertisementById is started");

        LOGGER.info("UserAdvertisementController | getAdvertisementById | advertisementId :  " + advertisementId);

        return ResponseEntity.status(HttpStatus.OK).body(advertisementService.getAdvertisement(advertisementId));
    }
}
