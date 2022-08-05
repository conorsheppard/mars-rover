package com.exercise.rover;

/**
* N, S, E, W
* */
public enum Orientation {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public static Orientation of(String o) {
        return switch (o) {
            case "N" -> NORTH;
            case "S" -> SOUTH;
            case "E" -> EAST;
            case "W" -> WEST;
            default -> throw new IllegalArgumentException();
        };
    }
}
