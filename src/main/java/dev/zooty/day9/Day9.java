package dev.zooty.day9;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day9 implements Day {
    private final int day = 9;

    @Override
    public String getSolution1() {
        var disk = new Disk(getInputString());
        disk.doDeFragment();
        return String.valueOf(disk.getChecksum());
    }

    @Override
    public String getSolution2() {
        return "";
    }
}
