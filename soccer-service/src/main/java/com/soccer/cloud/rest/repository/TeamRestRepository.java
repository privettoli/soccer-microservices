package com.soccer.cloud.rest.repository;

import com.soccer.cloud.domain.Team;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "team")
public interface TeamRestRepository extends PagingAndSortingRepository<Team, Integer> {
}
