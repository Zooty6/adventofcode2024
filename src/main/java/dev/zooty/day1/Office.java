package dev.zooty.day1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Office {
    private final List<Integer> listA = new ArrayList<>();
    private final List<Integer> listB = new ArrayList<>();
    private final Map<Integer, Integer> occurrenceB = new HashMap<>();

    public void addPair(int locationIdA, int locationIdB) {
        listA.add(locationIdA);
        listB.add(locationIdB);
        occurrenceB.put(locationIdB, occurrenceB.getOrDefault(locationIdB, 0) + 1);
    }

    public int measureTotalDistance() {
        sortLists();
        return IntStream.range(0, listA.size())
                .parallel()
                .map(index -> Math.abs(listA.get(index) - listB.get(index)))
                .sum();
    }

    public int measureSimilarity() {
        return listA.stream()
                .parallel()
                .mapToInt(idA -> idA * occurrenceB.getOrDefault(idA, 0))
                .sum();
    }

    private void sortLists() {
        listA.sort(Integer::compareTo);
        listB.sort(Integer::compareTo);
    }
}
