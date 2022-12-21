package com.exercise.service;

import com.exercise.controller.RoverController;
import com.exercise.grid.Grid;
import com.exercise.input.reader.InputReader;
import com.exercise.rover.Rover;

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

     public void execute() {
         readInput(rovers, grid);
         executeRoverCommands();
         generateOutput();
     }

     private void readInput(List<Rover> rovers, Grid grid) {
         inputReader.readUserInput(rovers, grid);
     }

    private void executeRoverCommands() {
        rovers.stream().filter(Predicate.not(Rover::isLost)).forEach(rover -> roverController.executeCommands(grid, rover));
    }

    private void generateOutput() {
        rovers.forEach(System.out::println);
    }
}
