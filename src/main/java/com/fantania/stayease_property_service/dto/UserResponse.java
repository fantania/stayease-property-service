package com.fantania.stayease_property_service.dto;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        String role
) {
}