package com.ticket_booking.inventory_service.service;

import com.ticket_booking.inventory_service.dto.request.CreateStadiumRequest;
import com.ticket_booking.inventory_service.dto.request.UpdateStadiumRequest;
import com.ticket_booking.inventory_service.dto.response.StadiumResponse;

import java.util.List;

public interface StadiumService {

    StadiumResponse create(CreateStadiumRequest request);

    List<StadiumResponse> getAll();

    StadiumResponse getById(Long id);

    StadiumResponse update(Long id, UpdateStadiumRequest request);

    void delete(Long id);
}