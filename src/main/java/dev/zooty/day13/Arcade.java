package dev.zooty.day13;

import java.util.Arrays;
import java.util.List;

public class Arcade {
    private final List<ClawMachine> machines;

    public Arcade(String input) {
        machines = Arrays.stream(input.split("\n\n"))
                .map(machineString -> {
                    String[] machineParts = machineString.split("\n");
                    return new ClawMachine(machineParts[0], machineParts[1], machineParts[2]);
                })
                .toList();
    }

    public int getMinimumTokens() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
