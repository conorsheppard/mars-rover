package com.exercise.controller;

import com.exercise.exception.LostRoverException;
import com.exercise.grid.Grid;
import com.exercise.rover.Command;
import com.exercise.rover.Rover;

import static com.exercise.rover.Orientation.*;

/**
* A class to encapsulate the execution of Rover commands.
**/
public class RoverController {

    public void executeCommands(Grid grid, Rover rover) {
        for (Command command : rover.getCommands())
            try { executeCommand(command, grid, rover); } catch (LostRoverException e) { break; }
    }

    private void executeCommand(Command command, Grid grid, Rover rover) throws LostRoverException {
        switch (command) {
            case MOVE_FORWARD -> moveForward(grid, rover);
            case TURN_LEFT -> rotateLeft(rover);
            case TURN_RIGHT -> rotateRight(rover);
        }
    }

    private void moveForward(Grid grid, Rover rover) throws LostRoverException {
        switch (rover.getOrientation()) {
            case NORTH -> moveNorth(grid, rover);
            case SOUTH -> moveSouth(rover);
            case EAST -> moveEast(grid, rover);
            case WEST -> moveWest(rover);
        }
    }

    private void moveNorth(Grid grid, Rover rover) throws LostRoverException {
        rover.setLost(grid.getHeight() < rover.getPosition().getYCoordinate()+1);
        if (rover.isLost()) throw new LostRoverException();
        rover.getPosition().setYCoordinate(rover.getPosition().getYCoordinate()+1);
    }

    private void moveSouth(Rover rover) throws LostRoverException {
        rover.setLost(0 > rover.getPosition().getYCoordinate()-1);
        if (rover.isLost()) throw new LostRoverException();
        rover.getPosition().setYCoordinate(rover.getPosition().getYCoordinate()-1);
    }

    private void moveEast(Grid grid, Rover rover) throws LostRoverException {
        rover.setLost(grid.getWidth() < rover.getPosition().getXCoordinate()+1);
        if (rover.isLost()) throw new LostRoverException();
        rover.getPosition().setXCoordinate(rover.getPosition().getXCoordinate()+1);
    }

    private void moveWest(Rover rover) throws LostRoverException {
        rover.setLost(0 > rover.getPosition().getXCoordinate()-1);
        if (rover.isLost()) throw new LostRoverException();
        rover.getPosition().setXCoordinate(rover.getPosition().getXCoordinate()-1);
    }

    private void rotateLeft(Rover rover) {
        switch (rover.getOrientation()) {
            case NORTH -> rover.setOrientation(WEST);
            case SOUTH -> rover.setOrientation(EAST);
            case EAST -> rover.setOrientation(NORTH);
            case WEST -> rover.setOrientation(SOUTH);
        }
    }

    private void rotateRight(Rover rover) {
        switch (rover.getOrientation()) {
            case NORTH -> rover.setOrientation(EAST);
            case SOUTH -> rover.setOrientation(WEST);
            case EAST -> rover.setOrientation(SOUTH);
            case WEST -> rover.setOrientation(NORTH);
        }
    }
}
