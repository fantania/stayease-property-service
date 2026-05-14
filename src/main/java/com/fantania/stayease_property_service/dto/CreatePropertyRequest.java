package com.fantania.stayease_property_service.dto;

import com.fantania.stayease_property_service.enums.PropertyType;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreatePropertyRequest(
        @NotNull(message = "Host ID is required")
        Long hostId,

        @NotBlank(message = "Title is required")
        String title,

        String description,

        @NotNull(message = "Property type is required")
        PropertyType propertyType,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "Country is required")
        String country,

        @NotNull(message = "Price per night is required")
        @DecimalMin(value = "1.00", message = "Price must be at least 1.00")
        BigDecimal pricePerNight
) {
}