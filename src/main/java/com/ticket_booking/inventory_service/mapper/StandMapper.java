package com.ticket_booking.inventory_service.mapper;

import com.ticket_booking.inventory_service.dto.request.CreateStandRequest;
import com.ticket_booking.inventory_service.dto.request.UpdateStandRequest;
import com.ticket_booking.inventory_service.dto.response.StandResponse;
import com.ticket_booking.inventory_service.entity.Stand;
import com.ticket_booking.inventory_service.entity.Stadium;
import org.springframework.stereotype.Component;

@Component
public class StandMapper {

    public Stand toEntity(CreateStandRequest request, Stadium stadium) {

        return Stand.builder()
                .name(request.getName())
                .type(request.getType())
                .capacity(request.getCapacity())
                .stadium(stadium)
                .build();
    }

    public void updateEntity(UpdateStandRequest request, Stand stand) {

        stand.setName(request.getName());
        stand.setType(request.getType());
        stand.setCapacity(request.getCapacity());
    }

    public StandResponse toResponse(Stand stand) {

        return StandResponse.builder()
                .id(stand.getId())
                .name(stand.getName())
                .type(stand.getType())
                .capacity(stand.getCapacity())
                .stadiumId(stand.getStadium().getId())
                .stadiumName(stand.getStadium().getName())
                .build();
    }
}