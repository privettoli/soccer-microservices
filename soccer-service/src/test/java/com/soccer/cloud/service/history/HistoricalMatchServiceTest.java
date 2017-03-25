package com.soccer.cloud.service.history;

import com.soccer.cloud.domain.Match;
import com.soccer.cloud.repository.history.AuditReaderProvider;
import com.soccer.cloud.repository.history.MatchRepository;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.AuditReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Optional;

import static java.time.Instant.now;
import static java.util.Date.from;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@FieldDefaults(level = PRIVATE)
@RunWith(MockitoJUnitRunner.class)
public class HistoricalMatchServiceTest {
    static final Integer RESOURCE_ID = 10;
    static final Integer REVISION_NUMBER = 3;
    @InjectMocks
    HistoricalMatchService service;
    @Mock
    MatchRepository matchRepository;
    @Mock
    AuditReaderProvider auditReaderProvider;
    @Mock
    AuditReader auditReader;

    @Test
    public void findOneNoResource() throws Exception {
        //GIVEN
        doReturn(empty()).when(matchRepository).findOne(RESOURCE_ID);
        //WHEN
        Optional<Match> result = service.findOne(RESOURCE_ID);
        //THEN
        verify(matchRepository).findOne(RESOURCE_ID);
        verify(auditReaderProvider, never()).provide();
        assertFalse(result.isPresent());
    }

    @Test
    public void findOneNoScheduledStartDate() throws Exception {
        //GIVEN
        Match match = new Match();
        doReturn(of(match)).when(matchRepository).findOne(RESOURCE_ID);
        //WHEN
        Optional<Match> result = service.findOne(RESOURCE_ID);
        //THEN
        verify(matchRepository).findOne(RESOURCE_ID);
        verify(auditReaderProvider, never()).provide();
        assertFalse(result.isPresent());
    }

    @Test
    public void findOneHistoryVersion() throws Exception {
        //GIVEN
        Match match = new Match();
        Instant scheduledStartDateTime = now();
        match.setScheduledStartDateTime(scheduledStartDateTime);
        doReturn(of(match)).when(matchRepository).findOne(RESOURCE_ID);
        doReturn(auditReader).when(auditReaderProvider).provide();
        doReturn(REVISION_NUMBER).when(auditReader).getRevisionNumberForDate(from(scheduledStartDateTime));
        Match auditedVersion = new Match();
        auditedVersion.setName("Audited");
        doReturn(auditedVersion).when(auditReader).find(Match.class, RESOURCE_ID, REVISION_NUMBER);
        //WHEN
        Optional<Match> result = service.findOne(RESOURCE_ID);
        //THEN
        verify(matchRepository).findOne(RESOURCE_ID);
        verify(auditReaderProvider).provide();
        verify(auditReader).getRevisionNumberForDate(from(scheduledStartDateTime));
        verify(auditReader).find(Match.class, RESOURCE_ID, REVISION_NUMBER);
        assertTrue(result.isPresent());
        //noinspection OptionalGetWithoutIsPresent
        assertEquals(auditedVersion, result.get());
    }
}