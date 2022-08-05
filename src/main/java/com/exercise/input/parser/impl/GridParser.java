package com.exercise.input.parser.impl;

import com.exercise.exception.IntegerOverflowException;
import com.exercise.grid.Grid;
import com.exercise.input.parser.interfaces.Parser;

public class GridParser implements Parser<Grid> {
    @Override
    public Grid parse(String s) throws IntegerOverflowException {
        var gridDimensions = s.trim().split(" ");
        var dimensionsChecked = Parser.checkInputBounds(gridDimensions);
        return new Grid(dimensionsChecked.get(0), dimensionsChecked.get(1));
    }
}
