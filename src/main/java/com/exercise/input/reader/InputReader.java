package com.exercise.input.reader;

import com.exercise.exception.GridInputException;
import com.exercise.exception.GridSizeException;
import com.exercise.exception.IntegerOverflowException;
import com.exercise.exception.RoverInputException;
import com.exercise.grid.Grid;
import com.exercise.input.parser.impl.GridParser;
import com.exercise.input.parser.impl.RoverParser;
import com.exercise.input.parser.interfaces.Parser;
import com.exercise.input.validator.impl.GridInputValidator;
import com.exercise.input.validator.impl.RoverInputValidator;
import com.exercise.input.validator.interfaces.InputValidator;
import com.exercise.rover.Rover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class InputReader {
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private final InputValidator roverInputValidator = new RoverInputValidator();
    private final InputValidator gridInputValidator = new GridInputValidator();
    private final Parser<Rover> roverParser = new RoverParser();
    private final Parser<Grid> gridParser = new GridParser();
    private final int MAX_USER_INPUT_ERRORS = 10;

    public void readUserInput(List<Rover> rovers, Grid grid) {
        var continueExecution = true;
        for (int i = 0; continueExecution && i < MAX_USER_INPUT_ERRORS; i++) {
            continueExecution = false;
            try {
                readInput(rovers, grid);
            } catch (GridInputException | RoverInputException | IOException | GridSizeException | IntegerOverflowException e) {
                continueExecution = true; rovers = new LinkedList<>();
            }
        }
    }

    public void readInput(List<Rover> rovers, Grid grid) throws GridInputException, RoverInputException, IOException, GridSizeException, IntegerOverflowException {
        readGridDimensions(grid);
        readRoverCommands(in, rovers, grid);
        in.close();
    }

    private void readGridDimensions(Grid grid) throws GridSizeException, IntegerOverflowException, IOException, GridInputException {
        var gridInput = validateGrid(in);
        var newGrid = gridParser.parse(gridInput);
        if (newGrid.getWidth() < 1 || newGrid.getHeight() < 1) {
            System.out.println("Error relating to Grid. Dimensions must be at least 1 x 1.");
            throw new GridSizeException();
        } else {
            grid.setHeight(newGrid.getHeight()).setWidth(newGrid.getWidth());
        }
    }

    private String validateGrid(BufferedReader in) throws GridInputException, IOException {
        var nextLine = in.readLine();
        try {
            gridInputValidator.validate(nextLine);
        } catch (Exception e) {
            System.out.println("Error relating to Grid input on line 1. Check input and start again.");
            throw new GridInputException();
        }
        return nextLine;
    }

    private void readRoverCommands(BufferedReader in, List<Rover> rovers, Grid grid) throws RoverInputException, IOException, IntegerOverflowException {
        for (int line = 2; ; line++) { // grid is 1st line of input, rovers start at line 2
            var nextLine = in.readLine();
            if (nextLine.isBlank() || nextLine.isEmpty()) break;
            try {
                roverInputValidator.validate(nextLine);
            } catch (Exception e) {
                System.out.println("Error relating to Rover input on line " + line + ". Check input and start again.");
                throw new RoverInputException();
            }
            var newRover = roverParser.parse(nextLine);
            if (newRover.getPosition().getYCoordinate() > grid.getHeight() ||
                    newRover.getPosition().getXCoordinate() > grid.getWidth()) {
                System.out.println("Rover landed off grid. Please check input and try again.");
                throw new RoverInputException();
            }
            rovers.add(newRover); // check if rover is lost from the get-go
        }
    }
}
