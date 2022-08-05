package com.exercise.input.parser.interfaces;

import com.exercise.exception.IntegerOverflowException;

import java.util.Arrays;
import java.util.List;

public interface Parser <T> {
    T parse(String s) throws IntegerOverflowException;

    /**
    * Checks that the coordinate input is within the bounds of a 32-bit integer (i.e. 2^31-1 or less)
    * */
    static List<Integer> checkInputBounds(String[] gridDimensions) throws IntegerOverflowException {
        var numsChecked = Arrays.stream(gridDimensions).map(Long::parseLong).filter(num -> num <= Integer.MAX_VALUE).map(Math::toIntExact).toList();
        if (numsChecked.size() < 2) {
            var e = new IntegerOverflowException();
            System.out.println(e.getMessage());
            throw e;
        }
        return numsChecked;
    }
}
