package com.exercise.rover;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
public class Position {
    private int xCoordinate;
    private int yCoordinate;
}
