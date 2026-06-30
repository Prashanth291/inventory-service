package com.ticket_booking.inventory_service.dto.request;

import com.ticket_booking.inventory_service.enums.StandType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request to create a stand")
public class CreateStandRequest {

    @Schema(
            description = "Stand name",
            example = "North Stand"
    )
    @NotBlank(message = "Stand name is required")
    @Size(max = 100)
    private String name;

    @Schema(
            description = "Stand type",
            example = "GENERAL"
    )
    @NotNull(message = "Stand type is required")
    private StandType type;

    @Schema(
            description = "Maximum capacity",
            example = "10000"
    )
    @Positive(message = "Capacity must be greater than zero")
    private Integer capacity;
}