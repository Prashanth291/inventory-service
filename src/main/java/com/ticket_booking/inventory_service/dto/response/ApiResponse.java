package com.ticket_booking.inventory_service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Standard API Response")
public class ApiResponse<T> {

    @Schema(example = "true")
    private boolean success;

    @Schema(example = "Stadium created successfully")
    private String message;

    private T data;

    private Object errors;
}