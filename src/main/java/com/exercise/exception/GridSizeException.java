package com.exercise.exception;

public class GridSizeException extends Exception {
    public GridSizeException() {
        super("Grid must have at least 1 x 1 dimensions");
    }
}
