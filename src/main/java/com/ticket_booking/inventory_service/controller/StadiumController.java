package com.ticket_booking.inventory_service.controller;

import com.ticket_booking.inventory_service.dto.request.CreateStadiumRequest;
import com.ticket_booking.inventory_service.dto.request.UpdateStadiumRequest;
import com.ticket_booking.inventory_service.dto.response.ApiResponse;
import com.ticket_booking.inventory_service.dto.response.StadiumResponse;
import com.ticket_booking.inventory_service.service.StadiumService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@Tag(
        name = "Stadium Management",
        description = "APIs for managing stadiums"
)
@RestController
@RequestMapping("/api/v1/stadiums")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService stadiumService;

    @Operation(
            summary = "Create Stadium",
            description = "Creates a new stadium."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Stadium created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "409", description = "Stadium already exists")
    })
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

    @Operation(
            summary = "Get All Stadiums",
            description = "Returns all stadiums."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Fetched successfully")
    })
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

    @Operation(
            summary = "Get Stadium By Id",
            description = "Returns a stadium by id."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stadium found"),
            @ApiResponse(responseCode = "404", description = "Stadium not found")
    })
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

    @Operation(
            summary = "Update Stadium",
            description = "Updates an existing stadium."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "404", description = "Stadium not found"),
            @ApiResponse(responseCode = "409", description = "Duplicate stadium")
    })
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

    @Operation(
            summary = "Delete Stadium",
            description = "Deletes a stadium."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Stadium not found")
    })
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