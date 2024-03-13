package com.springbootmicroservices.Managementservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmicroservices.Managementservice.model.Advertisement;
import com.springbootmicroservices.Managementservice.model.AdvertisementState;
import com.springbootmicroservices.Managementservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user_role")
public class UserController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserService UserService;

    @GetMapping("/alladvertisements")
    public ResponseEntity<List<Advertisement>> getAllAdvertisement() {
        LOGGER.info("UserController | getAllAdvertisements is started");

        return ResponseEntity.status(HttpStatus.OK).body(UserService.getAllAdvertisements());
    }

    public ResponseEntity<?> getAdvertisementById(String advertisementId) {
        LOGGER.info("UserController | getAdvertisementById is started");

        LOGGER.info("UserController | getAdvertisementById | advertisementId :  " + advertisementId);

        ResponseEntity<Advertisement> advertisementResponseEntity = UserService.getAdvertisementById(advertisementId);

        Advertisement advertisement = advertisementResponseEntity.getBody();

        LOGGER.info("UserController | getAdvertisementById | advertisement state :  " + advertisement.getState());

        if (advertisement.getState() == AdvertisementState.APPROVED) {
            return UserService.getAdvertisementById(advertisementId);
        } else {
            LOGGER.info("UserController | getAdvertisementById | Advertisement Not Found ");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Advertisement Not Found");
        }
    }
}
