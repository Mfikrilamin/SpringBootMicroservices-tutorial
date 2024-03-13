package com.springbootmicroservices.Reportservice.service;

import com.springbootmicroservices.Reportservice.dto.AdvertisementDto;

public interface ReportService {
    void createReport(AdvertisementDto advertisementDto);
}
