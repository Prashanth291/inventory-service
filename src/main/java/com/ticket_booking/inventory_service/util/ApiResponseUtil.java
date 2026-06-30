package com.ticket_booking.inventory_service.util;

import com.ticket_booking.inventory_service.dto.response.ApiResponse;

public class ApiResponseUtil {

    private ApiResponseUtil() {}

    public static <T> ApiResponse<T> success(String message, T data) {

        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static ApiResponse<Void> success(String message) {

        return ApiResponse.<Void>builder()
                .success(true)
                .message(message)
                .build();
    }

    public static ApiResponse<Void> error(String message) {

        return ApiResponse.<Void>builder()
                .success(false)
                .message(message)
                .build();
    }

    public static ApiResponse<Object> error(String message, Object errors) {

        return ApiResponse.builder()
                .success(false)
                .message(message)
                .errors(errors)
                .build();
    }
}