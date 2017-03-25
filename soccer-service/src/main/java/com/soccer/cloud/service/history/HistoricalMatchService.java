package com.soccer.cloud.service.history;

import com.soccer.cloud.domain.Match;
import com.soccer.cloud.repository.history.AuditReaderProvider;
import com.soccer.cloud.repository.history.MatchRepository;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.AuditReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Date.from;
import static lombok.AccessLevel.PRIVATE;

@Service
@FieldDefaults(level = PRIVATE)
public class HistoricalMatchService {
    MatchRepository matchRepository;
    AuditReaderProvider auditReaderProvider;

    @Autowired
    public HistoricalMatchService(AuditReaderProvider auditReaderProvider,
                                  MatchRepository matchRepository) {
        this.auditReaderProvider = auditReaderProvider;
        this.matchRepository = matchRepository;
    }

    @Transactional
    public Optional<Match> findOne(Integer id) {
        return matchRepository.findOne(id)
                .map(Match::getScheduledStartDateTime)
                .map(this.findAuditByMatchStartDateForMatchWith(id));
    }

    private Function<Instant, Match> findAuditByMatchStartDateForMatchWith(Integer id) {
        return (Instant matchStartDateTime) -> {
            AuditReader auditReader = auditReaderProvider.provide();
            Number revision = auditReader.getRevisionNumberForDate(from(matchStartDateTime));
            return auditReader.find(Match.class, id, revision);
        };
    }


}
