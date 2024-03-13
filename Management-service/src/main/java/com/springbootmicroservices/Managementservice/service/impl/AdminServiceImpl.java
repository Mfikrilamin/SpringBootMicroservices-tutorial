package com.springbootmicroservices.Managementservice.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springbootmicroservices.Managementservice.dto.AdvertisementRequest;
import com.springbootmicroservices.Managementservice.model.Advertisement;
import com.springbootmicroservices.Managementservice.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final String BASE_URL = "http://ADVERTISEMENT-SERVICE:9001/api/v1/admin_role";
    private static final String USER_BASE_URL = "http://USER-SERVICE:9000/api/v1/users";

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<?> createAdvertisement(AdvertisementRequest advertisementRequest, String userId) {
        LOGGER.info("AdminServiceImpl | createAdvertisement is started");

        String result = getRoleInfo();

        LOGGER.info("AdminServiceImpl | createAdvertisement | role result : " + result);

        if (result.equals("ROLE_ADMIN")) {
            return restTemplate.postForEntity(
                    BASE_URL + "/create/{userId}",
                    advertisementRequest,
                    String.class,
                    userId);
        }
        return null;
    }

    @Override
    public ResponseEntity<?> updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId) {
        LOGGER.info("AdminServiceImpl | updateAdvertisement is started");

        LOGGER.info("AdminServiceImpl | updateAdvertisement | advertisementId : " + advertisementId);

        String result = getRoleInfo();

        LOGGER.info("AdminServiceImpl | updateAdvertisement | role result : " + result);

        if (result.equals("ROLE_ADMIN")) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AdvertisementRequest> entity = new HttpEntity<>(advertisementRequest, httpHeaders);
            return restTemplate.exchange(BASE_URL + "/update/" + advertisementId, HttpMethod.PUT, entity, String.class);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> deleteAdvertisement(String advertisementId) {
        LOGGER.info("AdminServiceImpl | deleteAdvertisement is started");

        LOGGER.info("AdminServiceImpl | deleteAdvertisement | advertisementId : " + advertisementId);

        String result = getRoleInfo();

        LOGGER.info("AdminServiceImpl | deleteAdvertisement | role result : " + result);
        if (result.equals("ROLE_ADMIN")) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            return restTemplate.exchange(
                    BASE_URL + "/delete/" + advertisementId,
                    HttpMethod.DELETE,
                    entity,
                    String.class);
        }
        return null;
    }

    @Override
    public List<Advertisement> getAllAdvertisements() {
        LOGGER.info("AdminServiceImpl | getAllAdvertisements is started");

        String result = getRoleInfo();

        LOGGER.info("AdminServiceImpl | getAllAdvertisements | role result : " + result);

        if (result.equals("ROLE_ADMIN")) {
            ResponseEntity<Advertisement[]> restExchange = restTemplate.getForEntity(
                    BASE_URL + "/alladvertisements",
                    Advertisement[].class);
            return Arrays.asList(restExchange.getBody());
        }
        return null;
    }

    @Override
    public ResponseEntity<Advertisement> getAdvertisementById(String advertisementId) {
        LOGGER.info("AdminServiceImpl | getAdvertisementById is started");

        LOGGER.info("AdminServiceImpl | getAdvertisementById | advertisementId : " + advertisementId);

        String result = getRoleInfo();

        LOGGER.info("AdminServiceImpl | getAdvertisementById | role result : " + result);

        if (result.equals("ROLE_ADMIN")) {
            return restTemplate.getForEntity(BASE_URL + "/advertisement/{advertisementId}",
                    Advertisement.class,
                    advertisementId);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> approveAdvertisement(String advertisementId) {
        LOGGER.info("AdminServiceImpl | approveAdvertisement is started");

        LOGGER.info("AdminServiceImpl | approveAdvertisement | advertisementId : " + advertisementId);

        String result = getRoleInfo();

        LOGGER.info("AdminServiceImpl | approveAdvertisement | role result : " + result);

        if (result.equals("ROLE_ADMIN")) {
            return restTemplate.getForEntity(
                    BASE_URL + "/advertisement/{advertisementId}/approve",
                    String.class,
                    advertisementId);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> rejectAdvertisement(String advertisementId) {
        LOGGER.info("AdminServiceImpl | getAdvertisementById is started");

        LOGGER.info("AdminServiceImpl | getAdvertisementById | advertisementId : " + advertisementId);

        String result = getRoleInfo();

        LOGGER.info("AdminServiceImpl | approveAdvertisement | role result : " + result);

        if (result.equals("ROLE_ADMIN")) {
            return restTemplate.getForEntity(
                    BASE_URL + "/advertisement/{advertisementId}/reject",
                    String.class,
                    advertisementId);
        }

        return null;
    }

    private String getRoleInfo() {
        LOGGER.info("AdminServiceImpl | getRoleInfo is started");

        String result = this.restTemplate.getForObject(USER_BASE_URL + "/info", String.class);

        LOGGER.info("AdminServiceImpl | getRoleInfo | role result : " + result);

        return result;
    }
}
