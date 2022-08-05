package com.exercise;

import com.exercise.controller.RoverController;
import com.exercise.exception.IntegerOverflowException;
import com.exercise.grid.Grid;
import com.exercise.input.parser.impl.GridParser;
import com.exercise.input.parser.impl.RoverParser;
import com.exercise.input.parser.interfaces.Parser;
import com.exercise.rover.Rover;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoverControllerTest {
    private final Parser<Grid> gridParser = new GridParser();
    private final Parser<Rover> roverParser = new RoverParser();

    @ParameterizedTest
    @CsvSource({
            "4 4, '(0, 0, N) F', '(0, 1, N)'", // move north one space
            "4 4, '(1, 1, S) F', '(1, 0, S)'", // move south one space
            "4 4, '(0, 0, E) F', '(1, 0, E)'", // move east one space
            "4 4, '(1, 1, W) F', '(0, 1, W)'", // move west one space
            "4 4, '(0, 0, N) L', '(0, 0, W)'", // turn left, north to west
            "4 4, '(0, 0, N) R', '(0, 0, E)'", // turn right, north to east
            "4 4, '(0, 0, N) FFFFF', '(0, 4, N) LOST'", // move off-grid
//            "4 4, '(5, 5, N) L', '(5, 5, N) LOST'", // starts off-grid case is checked outside of the RoverController
    })
    @DisplayName("Test for RoverController executeCommands() method")
    void testExecuteCommands(ArgumentsAccessor args) throws IntegerOverflowException {
        var rover = roverParser.parse(args.getString(1));
        new RoverController().executeCommands(gridParser.parse(args.getString(0)), rover);
        assertEquals(args.getString(2), rover.toString());
    }
}
