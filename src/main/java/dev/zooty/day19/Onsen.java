package dev.zooty.day19;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Onsen {
    private final List<String> patterns;
    private final List<String> designs;

    public Onsen(BufferedReader reader) {
        patterns = reader.lines()
                .findFirst()
                .map(line -> List.of(line.split(", ")))
                .orElseThrow();

        designs = reader.lines()
                .skip(1)
                .toList();
    }

    public long countValidDesigns() {
        return designs.stream()
                .filter(this::isValid)
                .count();
    }

    public long countAmountOfValidDesigns() {
        return designs.stream()
                .mapToLong(this::calculateValidAmount)
                .sum();
    }

    private long calculateValidAmount(String design) {
        return countValidDesigns(design, new HashMap<>());
    }

    private long countValidDesigns(String design, Map<String, Long> cache) {
        if (design.isEmpty()) {
            return 1;
        }
        if (cache.containsKey(design)) {
            return cache.get(design);
        }
        long amount = patterns.stream()
                .filter(design::startsWith)
                .mapToLong(pattern -> countValidDesigns(design.substring(pattern.length()), cache))
                .sum();

        cache.put(design, amount);
        return amount;
    }

    private boolean isValid(String design) {
        return checkValidDesign(design, new HashMap<>());
    }

    private boolean checkValidDesign(String pattern, Map<String, Boolean> cache) {
        if (cache.containsKey(pattern)) {
            return cache.get(pattern);
        }
        if (patterns.stream().anyMatch(p -> p.equals(pattern))) {
            cache.put(pattern, Boolean.TRUE);
            return true;
        }
        boolean isValid = IntStream.range(1, pattern.length())
                .anyMatch(i -> checkValidDesign(pattern.substring(0, i), cache) && checkValidDesign(pattern.substring(i), cache));
        cache.put(pattern, isValid);
        return isValid;
    }
}
