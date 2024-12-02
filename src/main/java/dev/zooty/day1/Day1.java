package dev.zooty.day1;

import dev.zooty.Day;
import lombok.Getter;

@Getter
public class Day1 implements Day {
    private final int day = 1;
    private final Office office = new Office();

    public Day1() {
        getInputReader().lines()
                .map(line -> line.split("\\s+"))
                .forEach(pair -> office.addPair(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
    }

    @Override
    public String getSolution1() {
        return String.valueOf(office.measureTotalDistance());
    }

    @Override
    public String getSolution2() {
        return String.valueOf(office.measureSimilarity());
    }
}
