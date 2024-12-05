package dev.zooty.day5;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day5 implements Day {
    private final int day = 5;

    @Override
    public String getSolution1() {
        return String.valueOf(new SleighLaunchSafetyManual(getInputReader()).getCorrectlyOrderedNumber());
    }

    @Override
    public String getSolution2() {
        return String.valueOf(new SleighLaunchSafetyManual(getInputReader()).getFixedNumber());
    }
}
