package com.soccer.cloud.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

import javax.persistence.*;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@MappedSuperclass
@FieldDefaults(level = PROTECTED)
@Inheritance(strategy = TABLE_PER_CLASS)
@EntityListeners(AuditingEntityListener.class)
abstract class AuditableAbstractEntity implements Identifiable<Integer> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    Integer id;
    @CreatedDate
    @Column(name = "auditing_created_timestamp", updatable = false)
    Instant createdDate;
    @LastModifiedDate
    @Column(name = "auditing_last_modified_timestamp")
    Instant modifiedDate;
}
