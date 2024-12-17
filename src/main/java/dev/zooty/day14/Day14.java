package dev.zooty.day14;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day14 implements Day {
    private final int day = 14;

    public int getWidth() {
        return 101;
    }

    public int getHeight() {
        return 103;
    }

    @Override
    public String getSolution1() {
        Restroom restroom = new Restroom(getInputReader(), getWidth(), getHeight());
        restroom.move(100);
        return String.valueOf(restroom.getSafetyFactor());
    }

    @Override
    public String getSolution2() {
        Restroom restroom = new Restroom(getInputReader(), getWidth(), getHeight());
        return String.valueOf(restroom.getChristmasTreeCount());
    }
}
