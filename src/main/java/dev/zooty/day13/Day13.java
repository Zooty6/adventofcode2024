package dev.zooty.day13;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day13 implements Day {
    private final int day = 13;

    @Override
    public String getSolution1() {
        return String.valueOf(new Arcade(getInputString()).getMinimumTokens());
    }

    @Override
    public String getSolution2() {
        return "";
    }
}
