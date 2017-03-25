package com.soccer.cloud.repository.history;

import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

import static lombok.AccessLevel.PRIVATE;

@Component
@FieldDefaults(level = PRIVATE)
public class AuditReaderProvider {
    EntityManager entityManager;

    @Autowired
    public AuditReaderProvider(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public AuditReader provide() {
        return AuditReaderFactory.get(entityManager.unwrap(Session.class));
    }
}
