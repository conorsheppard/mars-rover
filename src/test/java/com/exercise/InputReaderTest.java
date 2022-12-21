package com.exercise;

import com.exercise.exception.GridInputException;
import com.exercise.exception.GridSizeException;
import com.exercise.exception.IntegerOverflowException;
import com.exercise.exception.RoverInputException;
import com.exercise.grid.Grid;
import com.exercise.input.reader.InputReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputReaderTest {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    Charset utf8 = StandardCharsets.UTF_8;

    @ParameterizedTest
    @CsvSource({
            " 4 4  ,' (2, 3, E) LFRFF ',''",
            " 4 4  ,' (2, 3, E)  ',''",
            "  4  ,' (2, 3, E)  ','Invalid input for Grid'",
            " 2147483647 4  ,' (2, 3, E)  ',''",
            " 2147483648 4  ,' (2, 3, E)  ','Input value(s) must be within the bounds of a signed 32 bit integer'",
            " 4 4  ,' (2147483647, 3, E)  ','Invalid input for Rover'", // off-grid landing
            " 4 4  ,' (2147483648, 3, E)  ','Input value(s) must be within the bounds of a signed 32 bit integer'",
            " 4 2000000000000000000000  ,'','Input value(s) must be within the bounds of a signed 32 bit integer'",
            " 4 4  ,' (, 3, E)  ','Invalid input for Rover'",
            " 4 4  ,' (, 3, E) GG ','Invalid input for Rover'",
            " -4 4  ,' (2, 3, E) LFRFF ','Invalid input for Grid'",
            " 4 4  ,' (-2, 3, E) LFRFF ','Invalid input for Rover'",
            " 1 1  ,' (0, 0, E) LF ',''",
    })
    @DisplayName("Test for InputReader readInput() method")
    public void testReadInput(ArgumentsAccessor args) throws IOException {
        out.write(args.getString(0).getBytes(utf8));
        out.write(new byte[]{'\n'});
        out.write(args.getString(1).getBytes(utf8));
        out.write(new byte[]{'\n', '\n'});

        System.setIn(new ByteArrayInputStream(out.toByteArray()));
        InputReader inputReader = new InputReader();
        String result = "";
        try {
            inputReader.readInput(new LinkedList<>(), new Grid());
        } catch (GridInputException | RoverInputException | IOException | GridSizeException | IntegerOverflowException e) {
            result = e.getMessage();
        }
        assertEquals(args.getString(2), result);
    }
}
