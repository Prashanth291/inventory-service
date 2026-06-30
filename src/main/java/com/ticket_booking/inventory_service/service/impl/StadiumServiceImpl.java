package com.ticket_booking.inventory_service.service.impl;

import com.ticket_booking.inventory_service.dto.request.CreateStadiumRequest;
import com.ticket_booking.inventory_service.dto.request.UpdateStadiumRequest;
import com.ticket_booking.inventory_service.dto.response.StadiumResponse;
import com.ticket_booking.inventory_service.entity.Stadium;
import com.ticket_booking.inventory_service.exception.DuplicateResourceException;
import com.ticket_booking.inventory_service.exception.ResourceNotFoundException;
import com.ticket_booking.inventory_service.mapper.StadiumMapper;
import com.ticket_booking.inventory_service.repository.StadiumRepository;
import com.ticket_booking.inventory_service.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;
    private final StadiumMapper stadiumMapper;

    @Override
    public StadiumResponse create(CreateStadiumRequest request) {

        validateDuplicateStadium(request.getName());

        Stadium stadium = stadiumMapper.toEntity(request);

        Stadium savedStadium = stadiumRepository.save(stadium);

        return stadiumMapper.toResponse(savedStadium);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StadiumResponse> getAll() {

        return stadiumRepository.findAll()
                .stream()
                .map(stadiumMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StadiumResponse getById(Long id) {

        Stadium stadium = getStadiumById(id);

        return stadiumMapper.toResponse(stadium);
    }

    @Override
    public StadiumResponse update(Long id, UpdateStadiumRequest request) {

        Stadium stadium = getStadiumById(id);

        if (!stadium.getName().equalsIgnoreCase(request.getName())) {
            validateDuplicateStadium(request.getName());
        }

        stadiumMapper.updateEntity(request, stadium);

        Stadium updatedStadium = stadiumRepository.save(stadium);

        return stadiumMapper.toResponse(updatedStadium);
    }

    @Override
    public void delete(Long id) {

        Stadium stadium = getStadiumById(id);

        stadiumRepository.delete(stadium);
    }

    private Stadium getStadiumById(Long id) {

        return stadiumRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Stadium not found with id: " + id
                        )
                );
    }

    private void validateDuplicateStadium(String stadiumName) {

        if (stadiumRepository.existsByNameIgnoreCase(stadiumName)) {
            throw new DuplicateResourceException(
                    "Stadium already exists with name: " + stadiumName
            );
        }
    }
}