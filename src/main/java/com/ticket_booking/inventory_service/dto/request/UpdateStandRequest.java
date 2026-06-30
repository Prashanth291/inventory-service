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
@Schema(description = "Request to update a stand")
public class UpdateStandRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private StandType type;

    @Positive
    private Integer capacity;
}