package com.exercise.input.validator.impl;

import com.exercise.exception.RoverInputException;
import com.exercise.input.validator.interfaces.InputValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoverInputValidator implements InputValidator {
    private final Pattern pattern = Pattern.compile("^(\\s*)\\(\\d+, \\d+, [NSEW]\\)(\\s*)[LRF]*(\\s*)$");

    @Override
    public void validate(String input) throws RoverInputException {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) throw new RoverInputException();
    }
}
