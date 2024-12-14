package dev.zooty.day12;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day12 implements Day {
    private final int day = 12;

    @Override
    public String getSolution1() {
        Garden garden = new Garden(getInputReader());
        garden.mapPlotRegions();
        return String.valueOf(garden.getPrice());
    }

    @Override
    public String getSolution2() {
        Garden garden = new Garden(getInputReader());
        garden.mapPlotRegions();
        return String.valueOf(garden.getDiscountedPrice());
    }
}
