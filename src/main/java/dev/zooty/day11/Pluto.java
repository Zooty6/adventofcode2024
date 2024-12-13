package dev.zooty.day11;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Pluto {
    private final Collection<Stone> stones;

    public Pluto(String inputString) {
        stones = Arrays.stream(inputString.split("\\s"))
                .map(Stone::new)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public long blink(int amount) {
        return stones.stream()
                .mapToLong(stone -> stone.blinkAmount(amount))
                .sum();
    }
}
