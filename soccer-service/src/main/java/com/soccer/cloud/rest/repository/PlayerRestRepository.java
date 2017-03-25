package com.soccer.cloud.rest.repository;

import com.soccer.cloud.domain.Player;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "player")
public interface PlayerRestRepository extends PagingAndSortingRepository<Player, Integer> {
}
