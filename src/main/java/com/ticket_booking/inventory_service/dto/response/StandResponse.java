package com.ticket_booking.inventory_service.dto.response;

import com.ticket_booking.inventory_service.enums.StandType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Stand response")
public class StandResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "North Stand")
    private String name;

    @Schema(example = "GENERAL")
    private StandType type;

    @Schema(example = "10000")
    private Integer capacity;

    @Schema(example = "1")
    private Long stadiumId;

    @Schema(example = "M. Chinnaswamy Stadium")
    private String stadiumName;
}