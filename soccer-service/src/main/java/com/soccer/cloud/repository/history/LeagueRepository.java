package com.soccer.cloud.repository.history;

import com.soccer.cloud.domain.League;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface LeagueRepository extends Repository<League, Integer> {
    Optional<League> findOne(Integer id);
}
