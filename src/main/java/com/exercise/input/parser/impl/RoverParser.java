package com.exercise.input.parser.impl;

import com.exercise.exception.IntegerOverflowException;
import com.exercise.input.parser.interfaces.Parser;
import com.exercise.rover.Command;
import com.exercise.rover.Orientation;
import com.exercise.rover.Position;
import com.exercise.rover.Rover;

import java.util.LinkedList;
import java.util.List;

import static com.exercise.rover.Command.*;

public class RoverParser implements Parser<Rover> {
    @Override
    public Rover parse(String s) throws IntegerOverflowException {
        var roverInput = s.trim().split("\\)");
        var roverPosition = roverInput[0].replace("(", "").split(", ");
        var coordinatesChecked = Parser.checkInputBounds(new String[]{roverPosition[0], roverPosition[1]});
        var roverCommands = parseRoverCommands(roverInput.length > 1 ? roverInput[1] : "");

        return new Rover()
                .setOrientation(Orientation.of(roverPosition[2]))
                .setCommands(roverCommands)
                .setPosition(new Position(coordinatesChecked.get(0), coordinatesChecked.get(1)))
                .setLost(false);
    }

    protected List<Command> parseRoverCommands(String commandStr) {
        var commands = new LinkedList<Command>();
        for (int i = 0; i < commandStr.length(); i++) {
            switch (commandStr.charAt(i)) {
                case 'L' -> commands.add(TURN_LEFT);
                case 'R' -> commands.add(TURN_RIGHT);
                case 'F' -> commands.add(MOVE_FORWARD);
            }
        }

        return commands;
    }
}
