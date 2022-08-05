package com.exercise;

import com.exercise.grid.Grid;
import com.exercise.input.parser.impl.GridParser;
import com.exercise.input.parser.impl.RoverParser;
import com.exercise.input.parser.interfaces.Parser;
import com.exercise.rover.Rover;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private final Parser<Grid> gridParser = new GridParser();
    private final Parser<Rover> roverParser = new RoverParser();

    @ParameterizedTest
    @CsvSource({
            "4 8, ''",
            " 4 4 , ''",
            " 0 0 , ''",
            "120 120, ''",
            "2147483648 2147483647, Input value(s) must be within the bounds of a signed 32 bit integer",
    })
    @DisplayName("Test for parsing of Grid input")
    public void testParseGrid(ArgumentsAccessor arguments) {
        String result = "";
        try {
            gridParser.parse(arguments.getString(0));
        } catch (Exception e) {
            result = e.getMessage();
        }
        assertEquals(arguments.getString(1), result);
    }

    @ParameterizedTest
    @CsvSource({
                    "'(4, 8, N) FFLR', ''",
                    "'(0, 8, N) ', ''",
                    "'(2147483648, 2147483647, W)', Input value(s) must be within the bounds of a signed 32 bit integer",
    })
    @DisplayName("Test for parsing of Rover input")
    public void testParseRover(ArgumentsAccessor arguments) {
        String result = "";
        try {
            roverParser.parse(arguments.getString(0));
        } catch (Exception e) {
            result = e.getMessage();
        }
        assertEquals(arguments.getString(1), result);
    }
}
