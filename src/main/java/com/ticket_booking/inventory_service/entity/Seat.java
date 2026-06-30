package com.ticket_booking.inventory_service.entity;

import com.ticket_booking.inventory_service.enums.SeatCategory;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_stand_row_seat",
                        columnNames = {
                                "stand_id",
                                "row_number",
                                "seat_number"
                        }
                )
        }
)
public class Seat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "stand_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_seats_stand")
    )
    private Stand stand;

    @Column(name = "row_number", nullable = false)
    private String rowNumber;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatCategory category;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;
}