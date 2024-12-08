package dev.zooty.day7;

import dev.zooty.Day;
import dev.zooty.Ignored;
import lombok.Getter;

@Getter
public class Day7 implements Day {
    private final int day = 7;

    @Override
    public String getSolution1() {
        return String.valueOf(getInputReader().lines()
                .map(Equation::new)
                .filter(Equation::test)
                .mapToLong(Equation::getTestValue)
                .sum());
    }

    @Override
    @Ignored(reason = "Takes a bit more than 3 seconds to run")
    public String getSolution2() {
        return String.valueOf(getInputReader().lines()
                .map(Equation::new)
                .filter(Equation::testWithAllOperators)
                .mapToLong(Equation::getTestValue)
                .sum());
    }
}
