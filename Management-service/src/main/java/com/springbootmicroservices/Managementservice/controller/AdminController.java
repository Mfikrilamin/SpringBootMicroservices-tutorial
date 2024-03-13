package com.springbootmicroservices.Managementservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmicroservices.Managementservice.dto.AdvertisementRequest;
import com.springbootmicroservices.Managementservice.model.Advertisement;
import com.springbootmicroservices.Managementservice.service.AdminService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin_role")
public class AdminController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final AdminService adminService;

    @PostMapping("/create/{userId}")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> createAdvertisement(@RequestBody AdvertisementRequest advertisementRequest,
            @PathVariable String userId) {
        LOGGER.info("AdminController | createAdvertisement is started");

        LOGGER.info("AdminController | createAdvertisement | userId : " + userId);

        adminService.createAdvertisement(advertisementRequest, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement Created");
    }

    @PutMapping("/update/{advertisementId}")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId) {
        LOGGER.info("AdminController | updateAdvertisement is started");

        LOGGER.info("AdminController | updateAdvertisement | advertisementId : " + advertisementId);

        adminService.updateAdvertisement(advertisementRequest, advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Updated");
    }

    @DeleteMapping("/delete/{advertisementId}")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> deleteAdvertisement(String advertisementId) {
        LOGGER.info("AdminController | deleteAdvertisement is started");

        LOGGER.info("AdminController | deleteAdvertisement | advertisementId : " + advertisementId);

        adminService.deleteAdvertisement(advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Deleted");
    }

    @GetMapping("/alladvertisements")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<List<Advertisement>> getAllAdvertisement() {
        LOGGER.info("AdminController | getAllAdvertisements is started");

        return ResponseEntity.ok(adminService.getAllAdvertisements());
    }

    @GetMapping("/alladvertisement/{advertisementId}")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<Advertisement> getAdvertisementById(String advertisementId) {
        LOGGER.info("AdminController | getAdvertisementById is started");

        LOGGER.info("AdminController | getAdvertisementById | advertisementId : " + advertisementId);

        return adminService.getAdvertisementById(advertisementId);
    }

    @GetMapping("/advertisement/{advertisementId}/approve")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> approveAdvertisement(String advertisementId) {
        LOGGER.info("AdminController | approveAdvertisement is started");

        LOGGER.info("AdminController | approveAdvertisement | advertisementId : " + advertisementId);

        return adminService.approveAdvertisement(advertisementId);
    }

    @GetMapping("/advertisement/{advertisementId}/reject")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> rejectAdvertisement(String advertisementId) {
        LOGGER.info("AdminController | rejectAdvertisement is started");

        LOGGER.info("AdminController | rejectAdvertisement | advertisementId : " + advertisementId);

        return adminService.rejectAdvertisement(advertisementId);
    }

    public ResponseEntity<?> managementFallback(Exception e) {
        LOGGER.info("AdminController | managementFallback is started");

        LOGGER.info(
                "AdminController | managementFallback | error : " + "CircuitBreaker Works! Error : " + e.getMessage());

        return new ResponseEntity<String>("CircuitBreaker Works! Error : " + e.getMessage(),
                HttpStatus.OK);
    }
}
