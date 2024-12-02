package dev.zooty.day2;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day2 implements Day {
    private final int day = 2;

    @Override
    public String getSolution1() {
        return String.valueOf(getInputReader()
                .lines()
                .map(Report::new)
                .filter(Report::isSafe)
                .count());
    }

    @Override
    public String getSolution2() {
        return String.valueOf(getInputReader()
                .lines()
                .map(Report::new)
                .filter(Report::isFaultTolerantSafe)
                .count());
    }

}