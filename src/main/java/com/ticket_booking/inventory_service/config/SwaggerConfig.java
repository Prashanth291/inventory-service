package com.ticket_booking.inventory_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI inventoryServiceOpenAPI() {

        return new OpenAPI()

                .info(
                        new Info()
                                .title("Ticket Booking Inventory Service API")
                                .description("""
                                        Inventory Service for IPL Ticket Booking System.

                                        Responsibilities:
                                        • Stadium Management
                                        • Stand Management
                                        • Seat Management
                                        • Match Management
                                        • Seat Inventory
                                        • Seat Locking
                                        • Seat Availability
                                        """)
                                .version("v1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Prasanth Kumar")
                                                .email("prashanthbollinedi2910@gmail.com")
                                )
                                .license(
                                        new License()
                                                .name("MIT")
                                )
                )

                .externalDocs(
                        new ExternalDocumentation()
                                .description("Project Documentation")
                                .url("https://github.com/prashanth291")
                );
    }
}