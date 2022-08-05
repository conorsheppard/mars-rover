package com.exercise.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Rover {
    private Orientation orientation;
    private List<Command> commands;
    private Position position;
    private boolean isLost;

    @Override
    public String toString() {
        return "(" +
                position.getXCoordinate() + ", " +
                position.getYCoordinate() + ", " +
                orientation.name().charAt(0) + ")" +
                (isLost ? " LOST" : "");
    }
}
