package dev.zooty.day9;

import dev.zooty.Day;
import dev.zooty.Ignored;
import lombok.Getter;

@Getter
public class Day9 implements Day {
    private final int day = 9;

    @Override
    @Ignored(reason = "Takes 20+ seconds to finish")
    public String getSolution1() {
        var disk = new Disk(getInputString());
        disk.doDeFragment();
        return String.valueOf(disk.getChecksum());
    }

    @Override
    @Ignored(reason = "Takes 20+ seconds to finish")
    public String getSolution2() {
        var disk = new Disk(getInputString());
        disk.doQuickDeFragment();
        return String.valueOf(disk.getChecksum());
    }
}
