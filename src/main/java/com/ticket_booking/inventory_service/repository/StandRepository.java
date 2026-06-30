package com.ticket_booking.inventory_service.repository;

import com.ticket_booking.inventory_service.entity.Stand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandRepository extends JpaRepository<Stand, Long> {

    /**
     * Checks if a stand with the given name already exists
     * within the specified stadium.
     */
    boolean existsByStadiumIdAndNameIgnoreCase(Long stadiumId, String name);

    /**
     * Returns all stands belonging to a stadium.
     */
    List<Stand> findByStadiumId(Long stadiumId);
}