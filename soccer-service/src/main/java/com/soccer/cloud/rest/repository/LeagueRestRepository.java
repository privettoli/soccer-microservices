package com.soccer.cloud.rest.repository;

import com.soccer.cloud.domain.League;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "league")
public interface LeagueRestRepository extends PagingAndSortingRepository<League, Integer> {
}
