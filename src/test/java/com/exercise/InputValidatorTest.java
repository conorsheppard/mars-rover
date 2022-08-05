package com.exercise;

import com.exercise.input.validator.impl.GridInputValidator;
import com.exercise.input.validator.impl.RoverInputValidator;
import com.exercise.input.validator.interfaces.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputValidatorTest {
    private final InputValidator gridValidator = new GridInputValidator();
    private final InputValidator roverValidator = new RoverInputValidator();

    @ParameterizedTest
    @CsvSource({
            "hello, Invalid input for Grid",
            "4 8, ''",
            " 4 4 , ''",
            " 42 , 'Invalid input for Grid'",
            "     8 8     , ''",
            "120 120, ''",
            "2147483648 2147483647, ''"
    })
    @DisplayName("Test for validation of Grid input")
    public void testGridValidate(ArgumentsAccessor arguments) {
        String result = "";
        try {
            gridValidator.validate(arguments.getString(0));
        } catch (Exception e) {
            result = e.getMessage();
        }
        assertEquals(arguments.getString(1), result);
    }

    @ParameterizedTest
    @CsvSource({
            "hello, Invalid input for Rover",
            "'(1, 2, E) F', ''",
            "'(0, 8, N) ', ''",
            "FFLR, Invalid input for Rover",
            "'0, 8, N) LLFFR', Invalid input for Rover"
    })
    @DisplayName("Test for validation of Rover input")
    public void testRoverValidate(ArgumentsAccessor arguments) {
        String result = "";
        try {
            roverValidator.validate(arguments.getString(0));
        } catch (Exception e) {
            result = e.getMessage();
        }
        assertEquals(arguments.getString(1), result);
    }
}
