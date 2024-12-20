package dev.zooty.day19;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day19 implements Day {
    private final int day = 19;

    @Override
    public String getSolution1() {
        return String.valueOf(new Onsen(getInputReader()).countValidDesigns());
    }

    @Override
    public String getSolution2() {
        return String.valueOf(new Onsen(getInputReader()).countAmountOfValidDesigns());
    }
}
