package com.ticket_booking.inventory_service.entity;

import com.ticket_booking.inventory_service.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "match_seats",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_match_seat",
                        columnNames = {
                                "match_id",
                                "seat_id"
                        }
                )
        },
        indexes = {
                @Index(
                        name = "idx_match_seats_match",
                        columnList = "match_id"
                ),
                @Index(
                        name = "idx_match_seats_seat",
                        columnList = "seat_id"
                ),
                @Index(
                        name = "idx_match_seats_status",
                        columnList = "match_id,status"
                ),
                @Index(
                        name = "idx_match_seats_lock_expiry",
                        columnList = "status,lock_expires_at"
                )
        }
)
public class MatchSeat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "match_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_match_seat_match")
    )
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "seat_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_match_seat_seat")
    )
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SeatStatus status;

    @Column(name = "locked_by")
    private UUID lockedBy;

    @Column(name = "lock_expires_at")
    private OffsetDateTime lockExpiresAt;

    @Column(name = "booking_id")
    private UUID bookingId;
}