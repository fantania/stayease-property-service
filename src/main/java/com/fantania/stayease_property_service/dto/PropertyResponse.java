package com.fantania.stayease_property_service.dto;

import com.fantania.stayease_property_service.enums.PropertyType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PropertyResponse(
        Long id,
        Long hostId,
        String title,
        String description,
        PropertyType propertyType,
        String city,
        String state,
        String country,
        BigDecimal pricePerNight,
        LocalDateTime createdAt
) {
}