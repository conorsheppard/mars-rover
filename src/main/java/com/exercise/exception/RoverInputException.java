package com.exercise.exception;

public class RoverInputException extends Exception{
    public RoverInputException() {
        super("Invalid input for Rover");
    }
}
