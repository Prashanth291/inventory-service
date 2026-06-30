package com.ticket_booking.inventory_service.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StadiumResponse {

    private Long id;

    private String name;

    private String city;

    private Integer capacity;
}