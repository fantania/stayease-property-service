package com.fantania.stayease_property_service.controller;

import com.fantania.stayease_property_service.dto.CreatePropertyRequest;
import com.fantania.stayease_property_service.dto.PropertyResponse;
import com.fantania.stayease_property_service.dto.UpdatePropertyRequest;
import com.fantania.stayease_property_service.enums.PropertyType;
import com.fantania.stayease_property_service.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyResponse createProperty(@Valid @RequestBody CreatePropertyRequest request) {
        return propertyService.createProperty(request);
    }

    @GetMapping
    public Page<PropertyResponse> getProperties(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) PropertyType propertyType,
            @RequestParam(required = false) Long hostId,
            Pageable pageable
    ) {
        return propertyService.getProperties(city, propertyType, hostId, pageable);
    }

    @GetMapping("/{id}")
    public PropertyResponse getPropertyById(@PathVariable Long id) {
        return propertyService.getPropertyById(id);
    }

    @PutMapping("/{id}")
    public PropertyResponse updateProperty(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePropertyRequest request
    ) {
        return propertyService.updateProperty(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
    }
}