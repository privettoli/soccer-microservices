package com.soccer.cloud.rest.validator;

import com.soccer.cloud.rest.validator.exception.InvalidResourceIdException;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class BasicRestControllerValidator {
    public void validateId(Integer id) {
        if (isNull(id) || id < 1) {
            throw new InvalidResourceIdException(id);
        }
    }
}
