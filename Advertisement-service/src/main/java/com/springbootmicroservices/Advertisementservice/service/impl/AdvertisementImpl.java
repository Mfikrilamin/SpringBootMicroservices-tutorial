package com.springbootmicroservices.Advertisementservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootmicroservices.Advertisementservice.model.Advertisement;
import com.springbootmicroservices.Advertisementservice.model.AdvertisementState;
import com.springbootmicroservices.Advertisementservice.repository.AdvertisementRepository;
import com.springbootmicroservices.Advertisementservice.service.AdvertisementService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdvertisementImpl implements AdvertisementService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdvertisementRepository advertisementRepository;

    @Override
    public List<Advertisement> getAllAdvertisements() {
        LOGGER.info("AdvertisementServiceImpl | getAllAdvertisements is started");

        List<Advertisement> advertisements = advertisementRepository.findAll();

        LOGGER.info("AdvertisementServiceImpl | getAllAdvertisements | advertiseList size : " + advertisements.size());

        advertisements.stream().filter(advertisement -> advertisement.getState() == AdvertisementState.APPROVED)
                .forEach(advertisement -> advertisement.setViewCount(advertisement.getViewCount() + 1));

        LOGGER.info("AdvertisementServiceImpl | getAllAdvertisements | advertiseList size : " + advertisements.size());

        return advertisements;
    }

    @Override
    public Advertisement getAdvertisement(String advertisementId) {
        LOGGER.info("AdvertisementServiceImpl | getAdvertisement is started");

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertisement.get();

        LOGGER.info(
                "AdvertisementServiceImpl | getAdvertisementById | advertisement title : " + advertisement.getTitle());

        advertisement.setViewCount(advertisement.getViewCount() + 1);

        return advertisement;
    }

}
