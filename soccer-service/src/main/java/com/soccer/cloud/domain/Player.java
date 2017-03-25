package com.soccer.cloud.domain;

import com.soccer.cloud.domain.enumeration.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@Audited
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    Integer id;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "middle_name")
    String middleName;
    @Column(name = "last_name")
    String lastName;
    @Enumerated(STRING)
    @Column(name = "position")
    PlayerPosition position;
    @Column(name = "birth_date")
    LocalDate birthDate;
}
