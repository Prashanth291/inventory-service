package com.ticket_booking.inventory_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStadiumRequest {

    @NotBlank(message = "Stadium name is required")
    @Size(max = 150, message = "Stadium name must not exceed 150 characters")
    private String name;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;

    @Positive(message = "Capacity must be greater than zero")
    private Integer capacity;
}