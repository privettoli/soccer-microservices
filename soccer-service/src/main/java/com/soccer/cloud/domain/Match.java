package com.soccer.cloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Audited
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matches")
@FieldDefaults(level = PRIVATE)
public class Match implements Identifiable<Integer> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "scheduled_start_timestamp")
    Instant scheduledStartDateTime;
    @ManyToOne
    @JoinColumn(name = "team_a_id")
    Team teamA;
    @ManyToOne
    @JoinColumn(name = "team_b_id")
    Team teamB;
}
