package com.exercise;

import com.exercise.service.RoverService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoverServiceTest {
    private final Charset utf8 = StandardCharsets.UTF_8;

    @ParameterizedTest
    @CsvSource("' 4 4  ',' (2, 3, E) LFRFF ','(4, 4, E)\n'")
    @DisplayName("Test for RoverService execute() method")
    public void testRoverService(ArgumentsAccessor args) throws IOException {
        var currentOutputStream = executeConsoleInputAndGetStream(args);
        new RoverService().execute();
        assertEquals(args.getString(2), currentOutputStream.toString());
    }

    @ParameterizedTest
    @CsvSource("'  4  ',' (2, 3, E) LFRFF ', 'Error relating to Grid input on line 1. Check input and start again.'")
    @DisplayName("Test for RoverService execute() method")
    public void testRoverServiceError(ArgumentsAccessor args) throws IOException {
        var currentOutputStream = executeConsoleInputAndGetStream(args);
        new RoverService().execute();
        assertEquals(0, currentOutputStream.toString().indexOf(args.getString(2)));
    }

    /**
    * This method is used to mock user console input for the tests.
    * */
    public ByteArrayOutputStream executeConsoleInputAndGetStream(ArgumentsAccessor args) throws IOException {
        var out = new ByteArrayOutputStream();
        out.write(args.getString(0).getBytes(utf8)); // user enters grid dimensions into console
        out.write(new byte[]{'\n'}); // user presses enter
        out.write(args.getString(1).getBytes(utf8)); // user enters rover position and commands
        out.write(new byte[]{'\n', '\n'}); // users presses enter and enter again to start execution
        System.setIn(new ByteArrayInputStream(out.toByteArray())); // point our input stream into System.in
        var currentOutputStream = new ByteArrayOutputStream(); // create an output stream to consume system out
        System.setOut(new PrintStream(currentOutputStream)); // redirect system out into the new output stream

        return currentOutputStream;
    }
}
