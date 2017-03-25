package com.soccer.cloud.service.history;

import com.soccer.cloud.domain.League;
import com.soccer.cloud.domain.Match;
import com.soccer.cloud.repository.history.LeagueRepository;
import lombok.experimental.FieldDefaults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptySet;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@FieldDefaults(level = PRIVATE)
@RunWith(MockitoJUnitRunner.class)
public class HistoricalLeagueServiceTest {
    static final Integer RESOURCE_ID = 10;
    @InjectMocks
    HistoricalLeagueService service;
    @Mock
    LeagueRepository leagueRepository;
    @Mock
    HistoricalMatchService matchService;

    @Test
    public void findOneNoResourceId() throws Exception {
        //GIVEN
        doReturn(empty()).when(leagueRepository).findOne(RESOURCE_ID);
        //WHEN
        Optional<League> result = service.findOne(RESOURCE_ID);
        //THEN
        verify(leagueRepository).findOne(RESOURCE_ID);
        verify(matchService, never()).findOne(any(Integer.class));
        assertFalse(result.isPresent());
    }

    @Test
    public void findOneNoMatchesLeague() throws Exception {
        //GIVEN
        int RESOURCE_ID = 10;
        League league = new League();
        league.setId(RESOURCE_ID);
        league.setMatches(emptySet());
        doReturn(of(league)).when(leagueRepository).findOne(RESOURCE_ID);
        //WHEN
        Optional<League> result = service.findOne(RESOURCE_ID);
        //THEN
        verify(leagueRepository).findOne(RESOURCE_ID);
        verify(matchService, never()).findOne(any(Integer.class));
        assertTrue(result.isPresent());
    }

    @Test
    public void findOneManyMatchesLeague() throws Exception {
        //GIVEN
        League league = new League();
        league.setId(RESOURCE_ID);
        Match match1 = new Match();
        match1.setId(1);
        Match match2 = new Match();
        match2.setId(2);
        Set<Match> matches = Stream.of(match1, match2).collect(Collectors.toSet());
        league.setMatches(matches);
        doReturn(of(league)).when(leagueRepository).findOne(RESOURCE_ID);
        doReturn(of(match1)).when(matchService).findOne(1);
        doReturn(of(match2)).when(matchService).findOne(2);
        //WHEN
        Optional<League> result = service.findOne(RESOURCE_ID);
        //THEN
        verify(leagueRepository).findOne(RESOURCE_ID);
        matches.forEach(match -> {
            verify(matchService).findOne(match.getId());
        });
        assertTrue(result.isPresent());
        //noinspection OptionalGetWithoutIsPresent
        assertEquals(matches, result.get().getMatches());
    }
}