package com.springbootmicroservices.Advertisementservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.springbootmicroservices.Advertisementservice.dto.AdvertisementDto;
import com.springbootmicroservices.Advertisementservice.model.Advertisement;
import com.springbootmicroservices.Advertisementservice.service.MessageService;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Override
    public void sendMessage(Advertisement advertisement) {
        LOGGER.info("MessageServiceImpl | sendMessage is started");

        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setId(advertisement.getId());
        advertisementDto.setTitle(advertisement.getTitle());
        advertisementDto.setViewCount(advertisement.getViewCount());

        LOGGER.info("MessageServiceImpl | sendMessage | | queue name : " + queue.getName());
        LOGGER.info("MessageServiceImpl | sendMessage | Sending message through RabbitMq");

        try {
            rabbitTemplate.convertAndSend(queue.getName(), advertisementDto);
        } catch (Exception e) {
            // TODO : To implement fallback/circuit breaker and retry mechanism
            LOGGER.info("MessageServiceImpl | sendMessage | error : " + e.getMessage());
        }
    }

}
