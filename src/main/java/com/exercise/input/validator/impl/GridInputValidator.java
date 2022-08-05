package com.exercise.input.validator.impl;

import com.exercise.exception.GridInputException;
import com.exercise.input.validator.interfaces.InputValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GridInputValidator implements InputValidator {
    private final Pattern pattern = Pattern.compile("^(\\s*)\\d+(\\s+)\\d+(\\s*)$");

    @Override
    public void validate(String input) throws GridInputException {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) throw new GridInputException();
    }
}
