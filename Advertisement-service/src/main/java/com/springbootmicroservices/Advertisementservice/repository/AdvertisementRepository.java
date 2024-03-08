package com.springbootmicroservices.Advertisementservice.repository;

import java.util.Optional;

import com.springbootmicroservices.Advertisementservice.model.Advertisement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    Optional<Advertisement> findById(Long id);
}
