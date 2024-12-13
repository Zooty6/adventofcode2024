package dev.zooty.day11;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day11 implements Day {
    private final int day = 11;

    @Override
    public String getSolution1() {
        return String.valueOf(new Pluto(getInputString()).blink(25));
    }

    @Override
    public String getSolution2() {
        return String.valueOf(new Pluto(getInputString()).blink(75));
    }
}
