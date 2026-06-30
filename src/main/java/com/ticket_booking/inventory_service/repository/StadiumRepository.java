package com.ticket_booking.inventory_service.repository;

import com.ticket_booking.inventory_service.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {

    boolean existsByNameIgnoreCase(String name);

}