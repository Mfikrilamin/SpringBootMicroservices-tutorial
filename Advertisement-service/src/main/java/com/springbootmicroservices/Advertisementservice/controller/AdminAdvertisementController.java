package com.springbootmicroservices.Advertisementservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmicroservices.Advertisementservice.dto.AdvertisementRequest;
import com.springbootmicroservices.Advertisementservice.model.Advertisement;
import com.springbootmicroservices.Advertisementservice.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin_role")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminAdvertisementController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdminService adminService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createAdvertisement(
            @RequestBody AdvertisementRequest advertisementRequest,
            @PathVariable String userId) {
        LOGGER.info("AdminAdvertisementController | createAdvertisement is started");

        adminService.createAdvertisement(advertisementRequest, userId);

        LOGGER.info("AdminAdvertisementController | createAdvertisement | Advertisement Created");

        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement Created");
    }

    @PutMapping("/update/{advertisementId}")
    public ResponseEntity<?> updateAdvertisement(
            @RequestBody AdvertisementRequest advertisementRequest,
            @PathVariable String advertisementId) {
        LOGGER.info("AdminAdvertisementController | updateAdvertisement is started");

        LOGGER.info("AdminAdvertisementController | updateAdvertisement | advertisementId : " + advertisementId);

        adminService.updateAdvertisement(advertisementRequest, advertisementId);

        LOGGER.info("AdminAdvertisementController | updateAdvertisement | Advertisement Updated");

        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Updated");
    }

    @GetMapping("/alladvertisements")
    public ResponseEntity<List<Advertisement>> getAllAdvertisement() {
        LOGGER.info("AdminAdvertisementController | getAllAdvertisements is started");

        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllAdvertisementsForAdmin());
    }

    @GetMapping("/advertisements/{advertisementId}")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable String advertisementId) {
        LOGGER.info("AdminAdvertisementController | getAdvertisementById is started");

        LOGGER.info("AdminAdvertisementController | getAdvertisementById | getAdvertisementById " + advertisementId);

        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAdvertisementByIdForAdmin(advertisementId));
    }

    @PutMapping("/advertisement/{advertisementId}/approve")
    public ResponseEntity<?> approveAdvertisement(@PathVariable String advertisementId) {
        LOGGER.info("AdminAdvertisementController | approveAdvertisement is started");

        LOGGER.info("AdminAdvertisementController | approveAdvertisement | getAdvertisementById " + advertisementId);

        Advertisement advertisement = null;

        try {
            advertisement = adminService.approveAdvertisement(advertisementId);
        } catch (Exception e) {
            LOGGER.info("AdminAdvertisementController | approveAdvertisement | error " + e.getMessage());
        }

        LOGGER.info("AdminAdvertisementController | approveAdvertisement | Approved ");

        return ResponseEntity.status(HttpStatus.OK).body(advertisement);
    }

    @PutMapping("/advertisement/{advertisementId}/reject")
    public ResponseEntity<?> rejectAdvertisement(@PathVariable String advertisementId) {
        LOGGER.info("AdminAdvertisementController | rejectAdvertisement is started");

        LOGGER.info("AdminAdvertisementController | rejectAdvertisement | getAdvertisementById " + advertisementId);

        Advertisement advertisement = null;

        try {
            advertisement = adminService.rejectAdvertisement(advertisementId);
        } catch (Exception e) {
            LOGGER.info("AdminAdvertisementController | rejectAdvertisement | error " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(advertisement);
    }
}
