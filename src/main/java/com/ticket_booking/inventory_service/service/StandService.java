package com.ticket_booking.inventory_service.service;

import com.ticket_booking.inventory_service.dto.request.CreateStandRequest;
import com.ticket_booking.inventory_service.dto.request.UpdateStandRequest;
import com.ticket_booking.inventory_service.dto.response.StandResponse;

import java.util.List;

public interface StandService {

    StandResponse create(Long stadiumId, CreateStandRequest request);

    List<StandResponse> getAllByStadium(Long stadiumId);

    StandResponse getById(Long id);

    StandResponse update(Long id, UpdateStandRequest request);

    void delete(Long id);
}