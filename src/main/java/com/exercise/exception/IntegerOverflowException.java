package com.exercise.exception;

public class IntegerOverflowException extends Exception {
    public IntegerOverflowException() {
        super("Input value(s) must be within the bounds of a signed 32 bit integer");
    }
}
