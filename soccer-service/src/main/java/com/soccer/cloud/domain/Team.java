package com.soccer.cloud.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Audited
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "team")
@FieldDefaults(level = PRIVATE)
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Team {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    Integer id;
    @Column(name = "name")
    String name;
    @ManyToMany(fetch = EAGER, cascade = {PERSIST, MERGE})
    @JoinTable(name = "teams_players",
            joinColumns = @JoinColumn(
                    name = "team_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "player_id",
                    referencedColumnName = "id"
            ))
    @AuditJoinTable(name = "teams_players_audit_log",
            inverseJoinColumns = @JoinColumn(
                    name = "player_id",
                    referencedColumnName = "id")
    )
    Set<Player> players;
}
