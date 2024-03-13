package com.springbootmicroservices.Reportservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.springbootmicroservices.Reportservice.dto.AdvertisementDto;
import com.springbootmicroservices.Reportservice.service.MessageService;
import com.springbootmicroservices.Reportservice.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@org.springframework.transaction.annotation.Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final ReportService reportService;

    @RabbitListener(queues = "${spring.rabbitmq.queues.adv-queue.name}")
    @Override
    public void receiveMessage(AdvertisementDto advertisementDto) {
        LOGGER.info("MessageServiceImpl | receiveMessage is started");

        LOGGER.info("MessageServiceImpl | receiveMessage | Report is creating");

        reportService.createReport(advertisementDto);
    }

}
