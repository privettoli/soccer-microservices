package com.soccer.cloud.rest;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.OK;

public abstract class AbstractRestController {
    protected <T> Function<T, ResponseEntity<T>> withLocationHeader(Supplier<ControllerLinkBuilder> location) {
        return (entity) -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(location.get().toUri());
            return new ResponseEntity<T>(entity, headers, OK);
        };
    }
}
