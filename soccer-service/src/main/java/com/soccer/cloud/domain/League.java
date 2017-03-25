package com.soccer.cloud.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Table(name = "league")
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class League extends AuditableAbstractEntity {
    @Column(name = "name")
    String name;
    @Formula("(SELECT min(matches.scheduled_start_timestamp) FROM matches WHERE matches.league_id = id)")
    Instant firstMatchDateTime;
    @Formula("(SELECT max(matches.scheduled_start_timestamp) FROM matches WHERE matches.league_id = id)")
    Instant lastMatchDateTime;
    @OneToMany(cascade = ALL)
    @JoinColumn(name = "league_id")
    Set<Match> matches;
}
