package dev.zooty.day18;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day18 implements Day {
    private final int day = 18;

    public int getMemorySize() {
        return 70;
    }

    public int getTimeSpentForFirstSolution() {
        return 1024;
    }

    @Override
    public String getSolution1() {
        return String.valueOf(new Memory(getInputReader(), getMemorySize()).getShortestRoute(getTimeSpentForFirstSolution()));
    }

    @Override
    public String getSolution2() {
        return String.valueOf(new Memory(getInputReader(), getMemorySize()).getTimeBeforeStuck(getTimeSpentForFirstSolution()));
    }
}
