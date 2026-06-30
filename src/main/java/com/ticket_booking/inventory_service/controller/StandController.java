package com.ticket_booking.inventory_service.controller;

import com.ticket_booking.inventory_service.dto.request.CreateStandRequest;
import com.ticket_booking.inventory_service.dto.request.UpdateStandRequest;
//import com.ticket_booking.inventory_service.dto.response.ApiResponse;
import com.ticket_booking.inventory_service.dto.response.ApiResponse;
import com.ticket_booking.inventory_service.dto.response.StandResponse;
import com.ticket_booking.inventory_service.service.StandService;
import com.ticket_booking.inventory_service.util.ApiResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse as SwaggerApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(
        name = "Stand Management",
        description = "APIs for managing stadium stands"
)
public class StandController {

    private final StandService standService;

    @Operation(
            summary = "Create Stand",
            description = "Creates a stand inside a stadium."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Stand created successfully"
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Stadium not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Stand already exists")
    })
    @PostMapping("/stadiums/{stadiumId}/stands")
    public ResponseEntity<ApiResponse<StandResponse>> createStand(
            @PathVariable Long stadiumId,
            @Valid @RequestBody CreateStandRequest request) {

        StandResponse response = standService.create(stadiumId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseUtil.success(
                        "Stand created successfully",
                        response
                ));
    }

    @Operation(
            summary = "Get All Stands",
            description = "Returns all stands of a stadium."
    )
    @GetMapping("/stadiums/{stadiumId}/stands")
    public ResponseEntity<ApiResponse<List<StandResponse>>> getAllStands(
            @PathVariable Long stadiumId) {

        List<StandResponse> response =
                standService.getAllByStadium(stadiumId);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Stands fetched successfully",
                        response
                )
        );
    }

    @Operation(
            summary = "Get Stand",
            description = "Returns stand details."
    )
    @GetMapping("/stands/{id}")
    public ResponseEntity<ApiResponse<StandResponse>> getStand(
            @PathVariable Long id) {

        StandResponse response =
                standService.getById(id);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Stand fetched successfully",
                        response
                )
        );
    }

    @Operation(
            summary = "Update Stand",
            description = "Updates an existing stand."
    )
    @PutMapping("/stands/{id}")
    public ResponseEntity<ApiResponse<StandResponse>> updateStand(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStandRequest request) {

        StandResponse response =
                standService.update(id, request);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Stand updated successfully",
                        response
                )
        );
    }

    @Operation(
            summary = "Delete Stand",
            description = "Deletes a stand."
    )
    @DeleteMapping("/stands/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStand(
            @PathVariable Long id) {

        standService.delete(id);

        return ResponseEntity.ok(
                ApiResponseUtil.success(
                        "Stand deleted successfully"
                )
        );
    }

}