package com.springbootmicroservices.Advertisementservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootmicroservices.Advertisementservice.convertor.AdvertismentMapper;
import com.springbootmicroservices.Advertisementservice.dto.AdvertisementRequest;
import com.springbootmicroservices.Advertisementservice.model.Advertisement;
import com.springbootmicroservices.Advertisementservice.model.AdvertisementState;
import com.springbootmicroservices.Advertisementservice.repository.AdvertisementRepository;
import com.springbootmicroservices.Advertisementservice.service.AdminService;
import com.springbootmicroservices.Advertisementservice.service.MessageService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdvertisementRepository advertisementRepository;
    private final MessageService messageService;

    @Override
    public void createAdvertisement(AdvertisementRequest advertisementRequest, String userId) {
        LOGGER.info("AdminServiceImpl | createAdvertisement is started");

        LOGGER.info("AdminServiceImpl | createAdvertisement | userId : " + userId);

        Advertisement createdAdvertisement = AdvertismentMapper
                .advertisementRequestToAdvertisement(advertisementRequest);
        createdAdvertisement.setUserId(Long.valueOf(userId));
        createdAdvertisement.setViewCount(1L);
        createdAdvertisement.setState(AdvertisementState.WAITING);

        LOGGER.info("AdminServiceImpl | createAdvertisement | createdAdvertisement state : "
                + createdAdvertisement.getState().toString());

        advertisementRepository.save(createdAdvertisement);
    }

    @Override
    public void updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId) {
        LOGGER.info("AdminServiceImpl | updateAdvertisement is started");

        LOGGER.info("AdminServiceImpl | updateAdvertisement | advertisementId : " + advertisementId);

        advertisementRepository.findById(Long.valueOf(advertisementId)).ifPresent(advertisement -> {
            advertisement.setTitle(advertisementRequest.getTitle());
            advertisement.setPrice(advertisementRequest.getPrice());
            advertisement.setState(AdvertisementState.WAITING);

            LOGGER.info("AdminServiceImpl | updateAdvertisement | updatedAdvertisement state : "
                    + advertisement.getState().toString());

            advertisementRepository.save(advertisement);
        });
    }

    @Override
    public void deleteAdvertisement(String advertisementId) {
        LOGGER.info("AdminServiceImpl | deleteAdvertisement is started");

        LOGGER.info("AdminServiceImpl | deleteAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));
        Advertisement deletedAdvertisement = optionalAdvertisement.get();

        LOGGER.info("AdminServiceImpl | deleteAdvertisement | deletedAdvertisement title : "
                + deletedAdvertisement.getTitle());

        advertisementRepository.delete(deletedAdvertisement);
    }

    @Override
    public Advertisement approveAdvertisement(String advertisementId) {
        LOGGER.info("AdminServiceImpl | approveAdvertisement is started");

        LOGGER.info("AdminServiceImpl | approveAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));
        Advertisement approvedAdvertisement = optionalAdvertisement.get();
        approvedAdvertisement.setState(AdvertisementState.APPROVED);

        LOGGER.info("AdminServiceImpl | approveAdvertisement | deletedAdvertisement title : "
                + approvedAdvertisement.getState());

        messageService.sendMessage(approvedAdvertisement);

        return advertisementRepository.saveAndFlush(approvedAdvertisement);
    }

    @Override
    public Advertisement rejectAdvertisement(String advertisementId) {
        LOGGER.info("AdminServiceImpl | rejectAdvertisement is started");

        LOGGER.info("AdminServiceImpl | rejectAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));
        Advertisement approvedAdvertisement = optionalAdvertisement.get();
        approvedAdvertisement.setState(AdvertisementState.REJECTED);

        LOGGER.info("AdminServiceImpl | rejectAdvertisement | deletedAdvertisement title : "
                + approvedAdvertisement.getState());

        messageService.sendMessage(approvedAdvertisement);

        return advertisementRepository.saveAndFlush(approvedAdvertisement);
    }

    @Override
    public List<Advertisement> getAllAdvertisementsForAdmin() {
        LOGGER.info("AdminServiceImpl | getAllAdvertisementsForAdmin is started");

        List<Advertisement> advertisements = advertisementRepository.findAll();

        LOGGER.info("AdminServiceImpl | getAllAdvertisements | advertiseList size : " + advertisements.size());

        return advertisements;
    }

    @Override
    public Advertisement getAdvertisementByIdForAdmin(String advertisementId) {
        LOGGER.info("AdminServiceImpl | getAdvertisementByIdForAdmin is started");

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertisement.get();

        LOGGER.info(
                "AdvertisementServiceImpl | getAdvertisementById | advertisement title : " + advertisement.getTitle());

        return advertisement;
    }

}
