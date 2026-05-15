package com.fantania.stayease_property_service.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI propertyServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("StayEase Property Service API")
                        .version("1.0")
                        .description("API for managing StayEase property listings"));
    }
}