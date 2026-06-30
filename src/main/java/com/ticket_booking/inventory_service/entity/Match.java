package com.ticket_booking.inventory_service.entity;

import com.ticket_booking.inventory_service.enums.MatchStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "home_team_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_match_home_team")
    )
    private Team homeTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "away_team_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_match_away_team")
    )
    private Team awayTeam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "stadium_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_match_stadium")
    )
    private Stadium stadium;

    @Column(name = "match_number", length = 20)
    private String matchNumber;

    @Column(name = "match_date", nullable = false)
    private OffsetDateTime matchDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MatchStatus status;
}