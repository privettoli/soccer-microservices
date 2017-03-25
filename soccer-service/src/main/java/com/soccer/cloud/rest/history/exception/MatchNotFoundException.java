package com.soccer.cloud.rest.history.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Match not found")
public class MatchNotFoundException extends RuntimeException {
}
