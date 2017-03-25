package com.soccer.cloud.repository.history;

import com.soccer.cloud.domain.Match;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface MatchRepository extends Repository<Match, Integer> {
    Optional<Match> findOne(Integer id);
}
