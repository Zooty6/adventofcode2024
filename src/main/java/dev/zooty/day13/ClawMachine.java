package dev.zooty.day13;

import dev.zooty.day6.Position;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ClawMachine {
    private static final String REGEX = ".*X[+|=](?<X>\\d+).*Y[+|=](?<Y>\\d+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    private final Position positionA;
    private final Position positionB;
    private final Position pricePosition;

    public ClawMachine(String aLine, String bLine, String priceLine) {
        MatchResult resultA = PATTERN.matcher(aLine).results().findAny().orElseThrow();
        positionA = new Position(Integer.parseInt(resultA.group("X")), Integer.parseInt(resultA.group("Y")));
        MatchResult resultB = PATTERN.matcher(bLine).results().findAny().orElseThrow();
        positionB = new Position(Integer.parseInt(resultB.group("X")), Integer.parseInt(resultB.group("Y")));
        MatchResult priceResult = PATTERN.matcher(priceLine).results().findAny().orElseThrow();
        pricePosition = new Position(Integer.parseInt(priceResult.group("X")), Integer.parseInt(priceResult.group("Y")));
    }
}
