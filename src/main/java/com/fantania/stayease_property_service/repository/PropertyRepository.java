package com.fantania.stayease_property_service.repository;

import com.fantania.stayease_property_service.entity.Property;
import com.fantania.stayease_property_service.enums.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    Page<Property> findByCityContainingIgnoreCase(String city, Pageable pageable);

    Page<Property> findByPropertyType(PropertyType propertyType, Pageable pageable);

    Page<Property> findByCityContainingIgnoreCaseAndPropertyType(
            String city,
            PropertyType propertyType,
            Pageable pageable
    );

    Page<Property> findByHostId(Long hostId, Pageable pageable);
}