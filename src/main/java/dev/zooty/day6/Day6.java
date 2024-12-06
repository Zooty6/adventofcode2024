package dev.zooty.day6;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day6 implements Day {
    private final int day = 6;

    @Override
    public String getSolution1() {
        return String.valueOf(new LabMap(getInputReader()).getPathLength());
    }

    @Override
    public String getSolution2() {
        return String.valueOf(new LabMap(getInputReader()).getNumberOfPossibleNewObstructions());
    }
}
