package com.soccer.cloud.rest.validator;

import com.soccer.cloud.rest.validator.exception.InvalidResourceIdException;
import lombok.experimental.FieldDefaults;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collection;
import java.util.Random;

import static java.util.Arrays.asList;
import static lombok.AccessLevel.PRIVATE;

@RunWith(JUnit4.class)
@FieldDefaults(level = PRIVATE)
public class BasicRestControllerValidatorTest {
    // When is gonna JUnit 5 be released?
    static final Collection<Integer> CORRECT_IDS = asList(
            1, 10, 1000, 12123, new Random().nextInt(1000) + 1
    );
    static final Integer ZERO_ID = 0;
    static final Integer NEGATIVE_ID = -100;
    static final Integer NULL_ID = null;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    BasicRestControllerValidator validator = new BasicRestControllerValidator();

    @Test
    public void validateCorrectId() throws Exception {
        //GIVEN
        //WHEN
        CORRECT_IDS.forEach(validator::validateId);
        //THEN no exceptions thrown
    }

    @Test
    public void validateNegativeId() throws Exception {
        //GIVEN
        thrown.expect(InvalidResourceIdException.class);
        thrown.expectMessage("Wrong resource id " + NEGATIVE_ID);
        //WHEN
        validator.validateId(NEGATIVE_ID);
        //THEN
    }

    @Test
    public void validateZeroId() throws Exception {
        //GIVEN
        thrown.expect(InvalidResourceIdException.class);
        thrown.expectMessage("Wrong resource id " + ZERO_ID);
        //WHEN
        validator.validateId(ZERO_ID);
        //THEN
    }

    @Test
    public void validateNullId() throws Exception {
        //GIVEN
        thrown.expect(InvalidResourceIdException.class);
        thrown.expectMessage("Wrong resource id " + NULL_ID);
        //WHEN
        validator.validateId(NULL_ID);
        //THEN
    }
}