package com.ticket_booking.inventory_service.mapper;

import com.ticket_booking.inventory_service.dto.request.CreateStadiumRequest;
import com.ticket_booking.inventory_service.dto.request.UpdateStadiumRequest;
import com.ticket_booking.inventory_service.dto.response.StadiumResponse;
import com.ticket_booking.inventory_service.entity.Stadium;
import org.springframework.stereotype.Component;

@Component
public class StadiumMapper {

    public Stadium toEntity(CreateStadiumRequest request) {

        return Stadium.builder()
                .name(request.getName())
                .city(request.getCity())
                .capacity(request.getCapacity())
                .build();
    }

    public void updateEntity(UpdateStadiumRequest request, Stadium stadium) {

        stadium.setName(request.getName());
        stadium.setCity(request.getCity());
        stadium.setCapacity(request.getCapacity());
    }

    public StadiumResponse toResponse(Stadium stadium) {

        return StadiumResponse.builder()
                .id(stadium.getId())
                .name(stadium.getName())
                .city(stadium.getCity())
                .capacity(stadium.getCapacity())
                .build();
    }
}