package dev.zooty.day8;

import dev.zooty.Day;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
public class Day8 implements Day {
    private final int day = 8;

    @Override
    public String getSolution1() {
        return String.valueOf(new AntennaMap(getInputReader())
                .getAntinodes(1)
                .size());
    }

    @SneakyThrows
    @Override
    public String getSolution2() {
        return String.valueOf(new AntennaMap(getInputReader())
                .getAntinodes(getInputReader().readLine().length())
                .size());
    }
}
