package dev.zooty.day4;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day4 implements Day {
    private final int day = 4;

    @Override
    public String getSolution1() {
        return String.valueOf(new XMASPuzzle(getInputReader()).xmasCount());
    }

    @Override
    public String getSolution2() {
        return String.valueOf(new XMASPuzzle(getInputReader()).xDashMasCount());
    }
}
