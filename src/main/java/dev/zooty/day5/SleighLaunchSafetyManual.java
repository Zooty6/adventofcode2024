package dev.zooty.day5;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class SleighLaunchSafetyManual {
    private final List<OrderingRule> rules = new ArrayList<>();
    private final List<Update> updates = new ArrayList<>();

    public SleighLaunchSafetyManual(BufferedReader inputReader) {
        inputReader.lines().forEach(this::parseInput);
    }

    public int getCorrectlyOrderedNumber() {
        return updates.stream()
                .filter(update -> update.correct(rules))
                .mapToInt(Update::getMiddlePageNumber)
                .sum();
    }

    public int getFixedNumber() {
        return updates.stream()
                .filter(update -> !update.correct(rules))
                .map(update -> update.fixWithRuleset(rules))
                .mapToInt(Update::getMiddlePageNumber)
                .sum();
    }

    private void parseInput(String line) {
        if (line.contains("|")) {
            rules.add(new OrderingRule(line));
        }
        if (line.contains(",")) {
            updates.add(new Update(line));
        }
    }
}
