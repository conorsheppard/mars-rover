package com.exercise.service;

import com.exercise.controller.RoverController;
import com.exercise.exception.GridInputException;
import com.exercise.exception.GridSizeException;
import com.exercise.exception.IntegerOverflowException;
import com.exercise.exception.RoverInputException;
import com.exercise.grid.Grid;
import com.exercise.input.reader.InputReader;
import com.exercise.rover.Rover;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
* The main service class. Executes methods for reading input, executing commands and generating the output.
* */
public class RoverService {
    private final RoverController roverController = new RoverController();
    private final InputReader inputReader = new InputReader();
    private List<Rover> rovers = new LinkedList<>();
    private final Grid grid = new Grid();
    private final int MAX_USER_INPUT_ERRORS = 10;

     public void execute() {
         readInput();
         executeRoverCommands();
         generateOutput();
     }

    private void readInput() {
        var continueExecution = true;
        for (int i = 0; continueExecution && i < MAX_USER_INPUT_ERRORS; i++) {
            continueExecution = false;
            try {
                inputReader.readInput(rovers, grid);
            } catch (GridInputException | RoverInputException | IOException | GridSizeException | IntegerOverflowException e) {
                continueExecution = true; rovers = new LinkedList<>();
            }
        }
    }

    private void executeRoverCommands() {
        rovers.stream().filter(Predicate.not(Rover::isLost)).forEach(rover -> roverController.executeCommands(grid, rover));
    }

    private void generateOutput() {
        rovers.forEach(System.out::println);
    }
}
