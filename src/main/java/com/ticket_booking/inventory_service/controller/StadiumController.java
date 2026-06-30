package com.ticket_booking.inventory_service.controller;

import com.ticket_booking.inventory_service.dto.request.CreateStadiumRequest;
import com.ticket_booking.inventory_service.dto.request.UpdateStadiumRequest;
import com.ticket_booking.inventory_service.dto.response.ApiResponse;
import com.ticket_booking.inventory_service.dto.response.StadiumResponse;
import com.ticket_booking.inventory_service.service.StadiumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stadiums")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @PostMapping
    public ResponseEntity<ApiResponse<StadiumResponse>> createStadium(
            @Valid @RequestBody CreateStadiumRequest request) {

        StadiumResponse response = stadiumService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.<StadiumResponse>builder()
                                .success(true)
                                .message("Stadium created successfully")
                                .data(response)
                                .build()
                );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StadiumResponse>>> getAllStadiums() {

        List<StadiumResponse> response = stadiumService.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<StadiumResponse>>builder()
                        .success(true)
                        .message("Stadiums fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StadiumResponse>> getStadiumById(
            @PathVariable Long id) {

        StadiumResponse response = stadiumService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<StadiumResponse>builder()
                        .success(true)
                        .message("Stadium fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StadiumResponse>> updateStadium(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStadiumRequest request) {

        StadiumResponse response = stadiumService.update(id, request);

        return ResponseEntity.ok(
                ApiResponse.<StadiumResponse>builder()
                        .success(true)
                        .message("Stadium updated successfully")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStadium(
            @PathVariable Long id) {

        stadiumService.delete(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Stadium deleted successfully")
                        .build()
        );
    }
}