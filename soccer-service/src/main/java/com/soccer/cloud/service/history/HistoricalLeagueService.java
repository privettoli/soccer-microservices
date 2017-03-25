package com.soccer.cloud.service.history;

import com.soccer.cloud.domain.League;
import com.soccer.cloud.domain.Match;
import com.soccer.cloud.repository.history.LeagueRepository;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE)
public class HistoricalLeagueService {
    LeagueRepository leagueRepository;
    HistoricalMatchService matchService;

    @Autowired
    public HistoricalLeagueService(LeagueRepository leagueRepository,
                                   HistoricalMatchService matchService) {
        this.leagueRepository = leagueRepository;
        this.matchService = matchService;
    }

    public Optional<League> findOne(Integer id) {
        Optional<League> league = leagueRepository.findOne(id);
        league.ifPresent(this::fetchHistoricalMatches);
        return league;
    }

    private void fetchHistoricalMatches(League league) {
        Set<Match> historicalMatches = ofNullable(league.getMatches())
                .map(matches -> matches.stream()
                        .map(Match::getId)
                        .map(matchService::findOne)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(toSet()))
                .orElseGet(Collections::emptySet);
        league.setMatches(historicalMatches);
    }
}
