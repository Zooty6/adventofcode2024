package dev.zooty.day2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Report {
    private final List<Integer> levels;

    public Report(String input) {
        levels = Arrays.stream(input.split("\\s"))
                .map(Integer::parseInt)
                .toList();
    }

    public boolean isSafe() {
        return isSafe(levels);
    }

    private boolean isSafe(List<Integer> levelsToCheck) {
        return IntStream.range(1, levelsToCheck.size())
                .map(index -> levelsToCheck.get(index - 1) - levelsToCheck.get(index))
                .map(difference -> levelsToCheck.getFirst() - levelsToCheck.get(1) < 0 ? difference * -1 : difference)
                .allMatch(difference -> difference > 0 && difference <= 3);
    }

    public boolean isFaultTolerantSafe() {
        return IntStream.range(0, levels.size())
                .mapToObj(this::removeFromList)
                .anyMatch(this::isSafe);
    }

    private ArrayList<Integer> removeFromList(int index) {
        var filteredList = new ArrayList<>(levels);
        filteredList.remove(index);
        return filteredList;
    }
}
