package com.ticket_booking.inventory_service.service.impl;

import com.ticket_booking.inventory_service.dto.request.CreateStandRequest;
import com.ticket_booking.inventory_service.dto.request.UpdateStandRequest;
import com.ticket_booking.inventory_service.dto.response.StandResponse;
import com.ticket_booking.inventory_service.entity.Stadium;
import com.ticket_booking.inventory_service.entity.Stand;
import com.ticket_booking.inventory_service.exception.DuplicateResourceException;
import com.ticket_booking.inventory_service.exception.ResourceNotFoundException;
import com.ticket_booking.inventory_service.mapper.StandMapper;
import com.ticket_booking.inventory_service.repository.StadiumRepository;
import com.ticket_booking.inventory_service.repository.StandRepository;
import com.ticket_booking.inventory_service.service.StandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StandServiceImpl implements StandService {

    private final StandRepository standRepository;
    private final StadiumRepository stadiumRepository;
    private final StandMapper standMapper;

    @Override
    public StandResponse create(Long stadiumId, CreateStandRequest request) {

        log.info("Creating stand '{}' for stadium {}", request.getName(), stadiumId);

        Stadium stadium = getStadiumById(stadiumId);

        validateDuplicateStand(stadiumId, request.getName());

        Stand stand = standMapper.toEntity(request, stadium);

        Stand savedStand = standRepository.save(stand);

        log.info("Stand '{}' created successfully with id {}",
                savedStand.getName(),
                savedStand.getId());

        return standMapper.toResponse(savedStand);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StandResponse> getAllByStadium(Long stadiumId) {

        getStadiumById(stadiumId);

        return standRepository.findByStadiumId(stadiumId)
                .stream()
                .map(standMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StandResponse getById(Long id) {

        Stand stand = getStandById(id);

        return standMapper.toResponse(stand);
    }

    @Override
    public StandResponse update(Long id, UpdateStandRequest request) {

        Stand stand = getStandById(id);

        if (!stand.getName().equalsIgnoreCase(request.getName())) {
            validateDuplicateStand(
                    stand.getStadium().getId(),
                    request.getName()
            );
        }

        standMapper.updateEntity(request, stand);

        Stand updatedStand = standRepository.save(stand);

        log.info("Stand {} updated successfully", id);

        return standMapper.toResponse(updatedStand);
    }

    @Override
    public void delete(Long id) {

        Stand stand = getStandById(id);

        standRepository.delete(stand);

        log.info("Stand {} deleted successfully", id);
    }

    private Stadium getStadiumById(Long stadiumId) {

        return stadiumRepository.findById(stadiumId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Stadium not found with id: " + stadiumId
                        )
                );
    }

    private Stand getStandById(Long standId) {

        return standRepository.findById(standId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Stand not found with id: " + standId
                        )
                );
    }

    private void validateDuplicateStand(Long stadiumId, String standName) {

        if (standRepository.existsByStadiumIdAndNameIgnoreCase(stadiumId, standName)) {

            throw new DuplicateResourceException(
                    "Stand already exists with name '" +
                            standName +
                            "' in stadium id " +
                            stadiumId
            );
        }
    }
}