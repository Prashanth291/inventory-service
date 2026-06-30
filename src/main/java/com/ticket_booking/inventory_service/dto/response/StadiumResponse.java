package com.ticket_booking.inventory_service.dto.response;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Stadium response")
public class StadiumResponse {

    @Schema(
            description = "Unique stadium identifier",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Stadium name",
            example = "M. Chinnaswamy Stadium"
    )
    private String name;

    @Schema(
            description = "City",
            example = "Bengaluru"
    )
    private String city;

    @Schema(
            description = "Maximum seating capacity",
            example = "40000"
    )
    private Integer capacity;
}