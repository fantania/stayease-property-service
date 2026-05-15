package com.fantania.stayease_property_service.service;

import com.fantania.stayease_property_service.dto.CreatePropertyRequest;
import com.fantania.stayease_property_service.dto.PropertyResponse;
import com.fantania.stayease_property_service.dto.UpdatePropertyRequest;
import com.fantania.stayease_property_service.enums.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PropertyService {

    PropertyResponse createProperty(CreatePropertyRequest request);

    Page<PropertyResponse> getProperties(String city, PropertyType propertyType, Long hostId, Pageable pageable);

    PropertyResponse getPropertyById(Long id);

    PropertyResponse updateProperty(Long id, UpdatePropertyRequest request);

    void deleteProperty(Long id);
}