package com.soccer.cloud.rest.history;

import com.soccer.cloud.domain.Match;
import com.soccer.cloud.rest.AbstractRestController;
import com.soccer.cloud.rest.history.exception.MatchNotFoundException;
import com.soccer.cloud.rest.validator.BasicRestControllerValidator;
import com.soccer.cloud.service.history.HistoricalMatchService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@FieldDefaults(level = PRIVATE)
@RequestMapping(path = "history/match")
public class HistoricalMatchRestController extends AbstractRestController {
    HistoricalMatchService service;
    BasicRestControllerValidator validator;

    @Autowired
    public HistoricalMatchRestController(HistoricalMatchService service,
                                         BasicRestControllerValidator validator) {
        this.service = service;
        this.validator = validator;
    }

    @GetMapping(path = "{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Match> findOne(@PathVariable("id") Integer id) {
        validator.validateId(id);

        return service.findOne(id)
                .map(withLocationHeader(() -> linkTo(methodOn(HistoricalMatchRestController.class).findOne(id))))
                .orElseThrow(MatchNotFoundException::new);
    }
}
