package com.fantania.stayease_property_service.dto;

public record ServiceTokenRequest(
        String clientId,
        String clientSecret
) {
}