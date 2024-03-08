package com.springbootmicroservices.Advertisementservice.service;

import com.springbootmicroservices.Advertisementservice.model.Advertisement;

public interface MessageService {
    public void sendMessage(Advertisement advertisement);
}
