package com.soccer.cloud.rest.validator.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "Resource id cannot be less than 1")
public class InvalidResourceIdException extends RuntimeException {
    public InvalidResourceIdException(Integer id) {
        super("Wrong resource id " + id);
    }
}
