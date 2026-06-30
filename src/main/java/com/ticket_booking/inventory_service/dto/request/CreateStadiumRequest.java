package com.ticket_booking.inventory_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStadiumRequest {

    @Schema(
            description = "Stadium name",
            example = "M. Chinnaswamy Stadium"
    )
    @NotBlank(message = "Stadium name is required")
    @Size(max = 150)
    private String name;

    @Schema(
            description = "City where the stadium is located",
            example = "Bengaluru"
    )
    @NotBlank
    @Size(max = 100)
    private String city;

    @Schema(
            description = "Maximum seating capacity",
            example = "40000"
    )
    @Positive
    private Integer capacity;
}