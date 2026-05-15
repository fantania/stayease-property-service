package com.fantania.stayease_property_service.service;

import com.fantania.stayease_property_service.dto.CreatePropertyRequest;
import com.fantania.stayease_property_service.dto.PropertyResponse;
import com.fantania.stayease_property_service.dto.UpdatePropertyRequest;
import com.fantania.stayease_property_service.entity.Property;
import com.fantania.stayease_property_service.enums.PropertyType;
import com.fantania.stayease_property_service.exception.PropertyNotFoundException;
import com.fantania.stayease_property_service.repository.PropertyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyResponse createProperty(CreatePropertyRequest request) {
        Property property = Property.builder()
                .hostId(request.hostId())
                .title(request.title())
                .description(request.description())
                .propertyType(request.propertyType())
                .city(request.city())
                .state(request.state())
                .country(request.country())
                .pricePerNight(request.pricePerNight())
                .createdAt(LocalDateTime.now())
                .build();

        Property savedProperty = propertyRepository.save(property);

        return mapToResponse(savedProperty);
    }

    @Override
    public Page<PropertyResponse> getProperties(
            String city,
            PropertyType propertyType,
            Long hostId,
            Pageable pageable
    ) {
        boolean hasCity = city != null && !city.isBlank();
        boolean hasPropertyType = propertyType != null;
        boolean hasHostId = hostId != null;

        if (hasHostId) {
            return propertyRepository.findByHostId(hostId, pageable)
                    .map(this::mapToResponse);
        }

        if (hasCity && hasPropertyType) {
            return propertyRepository
                    .findByCityContainingIgnoreCaseAndPropertyType(city, propertyType, pageable)
                    .map(this::mapToResponse);
        }

        if (hasCity) {
            return propertyRepository
                    .findByCityContainingIgnoreCase(city, pageable)
                    .map(this::mapToResponse);
        }

        if (hasPropertyType) {
            return propertyRepository
                    .findByPropertyType(propertyType, pageable)
                    .map(this::mapToResponse);
        }

        return propertyRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public PropertyResponse getPropertyById(Long id) {
        Property property = findPropertyOrThrow(id);
        return mapToResponse(property);
    }

    @Override
    public PropertyResponse updateProperty(Long id, UpdatePropertyRequest request) {
        Property property = findPropertyOrThrow(id);

        property.setTitle(request.title());
        property.setDescription(request.description());
        property.setPropertyType(request.propertyType());
        property.setCity(request.city());
        property.setState(request.state());
        property.setCountry(request.country());
        property.setPricePerNight(request.pricePerNight());

        Property updatedProperty = propertyRepository.save(property);

        return mapToResponse(updatedProperty);
    }

    @Override
    public void deleteProperty(Long id) {
        Property property = findPropertyOrThrow(id);
        propertyRepository.delete(property);
    }

    private Property findPropertyOrThrow(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException(id));
    }

    private PropertyResponse mapToResponse(Property property) {
        return new PropertyResponse(
                property.getId(),
                property.getHostId(),
                property.getTitle(),
                property.getDescription(),
                property.getPropertyType(),
                property.getCity(),
                property.getState(),
                property.getCountry(),
                property.getPricePerNight(),
                property.getCreatedAt()
        );
    }
}