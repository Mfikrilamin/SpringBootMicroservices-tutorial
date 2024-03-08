package com.springbootmicroservices.Advertisementservice.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "advertisement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement extends IdBasedModel implements Serializable {
    private Long userId;
    private String title;
    private BigDecimal price;
    private Long viewCount;

    @Enumerated(EnumType.STRING)
    private AdvertisementState state;
}
