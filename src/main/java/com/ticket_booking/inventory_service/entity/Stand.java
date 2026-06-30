package com.ticket_booking.inventory_service.entity;

import com.ticket_booking.inventory_service.enums.StandType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "stands",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_stadium_stand",
                        columnNames = {"stadium_id", "name"}
                )
        }
)
public class Stand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "stadium_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_stands_stadium")
    )
    private Stadium stadium;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StandType type;

    @Column(nullable = false)
    private Integer capacity;
}