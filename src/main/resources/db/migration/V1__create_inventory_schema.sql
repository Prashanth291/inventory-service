-- ============================================
-- Teams
-- ============================================

CREATE TABLE teams (
                       id BIGSERIAL PRIMARY KEY,

                       name VARCHAR(100) NOT NULL,
                       short_name VARCHAR(10) NOT NULL,
                       logo_url VARCHAR(500),
                       city VARCHAR(100),

                       created_at TIMESTAMPTZ NOT NULL,
                       updated_at TIMESTAMPTZ NOT NULL,

                       CONSTRAINT uk_team_short_name UNIQUE(short_name)
);

-- ============================================
-- Stadiums
-- ============================================

CREATE TABLE stadiums (
                          id BIGSERIAL PRIMARY KEY,

                          name VARCHAR(150) NOT NULL,
                          city VARCHAR(100) NOT NULL,
                          capacity INTEGER NOT NULL,

                          created_at TIMESTAMPTZ NOT NULL,
                          updated_at TIMESTAMPTZ NOT NULL,

                          CONSTRAINT uk_stadium_name UNIQUE(name)
);

-- ============================================
-- Stands
-- ============================================

CREATE TABLE stands (
                        id BIGSERIAL PRIMARY KEY,

                        stadium_id BIGINT NOT NULL,

                        name VARCHAR(100) NOT NULL,
                        type VARCHAR(30) NOT NULL,
                        capacity INTEGER NOT NULL,

                        created_at TIMESTAMPTZ NOT NULL,
                        updated_at TIMESTAMPTZ NOT NULL,

                        CONSTRAINT fk_stands_stadium
                            FOREIGN KEY (stadium_id)
                                REFERENCES stadiums(id)
                                ON DELETE CASCADE,

                        CONSTRAINT uk_stadium_stand
                            UNIQUE(stadium_id, name)
);

-- ============================================
-- Seats
-- ============================================

CREATE TABLE seats (
                       id BIGSERIAL PRIMARY KEY,

                       stand_id BIGINT NOT NULL,

                       row_number VARCHAR(10) NOT NULL,
                       seat_number VARCHAR(10) NOT NULL,

                       category VARCHAR(30) NOT NULL,
                       base_price NUMERIC(10,2) NOT NULL,

                       created_at TIMESTAMPTZ NOT NULL,
                       updated_at TIMESTAMPTZ NOT NULL,

                       CONSTRAINT fk_seats_stand
                           FOREIGN KEY (stand_id)
                               REFERENCES stands(id)
                               ON DELETE CASCADE,

                       CONSTRAINT uk_stand_row_seat
                           UNIQUE(stand_id, row_number, seat_number)
);

-- ============================================
-- Matches
-- ============================================

CREATE TABLE matches (
                         id BIGSERIAL PRIMARY KEY,

                         home_team_id BIGINT NOT NULL,
                         away_team_id BIGINT NOT NULL,
                         stadium_id BIGINT NOT NULL,

                         match_number VARCHAR(20),
                         match_date TIMESTAMPTZ NOT NULL,

                         status VARCHAR(30) NOT NULL,

                         created_at TIMESTAMPTZ NOT NULL,
                         updated_at TIMESTAMPTZ NOT NULL,

                         CONSTRAINT fk_match_home_team
                             FOREIGN KEY (home_team_id)
                                 REFERENCES teams(id),

                         CONSTRAINT fk_match_away_team
                             FOREIGN KEY (away_team_id)
                                 REFERENCES teams(id),

                         CONSTRAINT fk_match_stadium
                             FOREIGN KEY (stadium_id)
                                 REFERENCES stadiums(id),

                         CONSTRAINT chk_different_teams
                             CHECK (home_team_id <> away_team_id)
);

-- ============================================
-- Match Seats
-- ============================================

CREATE TABLE match_seats (
                             id BIGSERIAL PRIMARY KEY,

                             match_id BIGINT NOT NULL,
                             seat_id BIGINT NOT NULL,

                             status VARCHAR(20) NOT NULL,

                             locked_by UUID,
                             lock_expires_at TIMESTAMPTZ,

                             booking_id UUID,

                             created_at TIMESTAMPTZ NOT NULL,
                             updated_at TIMESTAMPTZ NOT NULL,

                             CONSTRAINT fk_match_seat_match
                                 FOREIGN KEY (match_id)
                                     REFERENCES matches(id)
                                     ON DELETE CASCADE,

                             CONSTRAINT fk_match_seat_seat
                                 FOREIGN KEY (seat_id)
                                     REFERENCES seats(id)
                                     ON DELETE CASCADE,

                             CONSTRAINT uk_match_seat
                                 UNIQUE(match_id, seat_id)
);

-- ============================================
-- Indexes
-- ============================================

CREATE INDEX idx_stands_stadium
    ON stands(stadium_id);

CREATE INDEX idx_seats_stand
    ON seats(stand_id);

CREATE INDEX idx_matches_date
    ON matches(match_date);

CREATE INDEX idx_match_seats_match
    ON match_seats(match_id);

CREATE INDEX idx_match_seats_seat
    ON match_seats(seat_id);

CREATE INDEX idx_match_seats_status
    ON match_seats(match_id, status);

CREATE INDEX idx_match_seats_lock_expiry
    ON match_seats(status, lock_expires_at);