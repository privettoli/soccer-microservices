package com.soccer.cloud.rest.repository;

import com.soccer.cloud.domain.Match;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "match")
public interface MatchRestRepository extends PagingAndSortingRepository<Match, Integer> {
}
