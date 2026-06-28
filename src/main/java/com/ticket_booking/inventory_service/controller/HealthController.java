package com.ticket_booking.inventory_service.controller;

import com.ticket_booking.inventory_service.dto.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.<String>builder()
                .success(true)
                .message("Inventory Service is running")
                .data("UP")
                .build();
    }
}