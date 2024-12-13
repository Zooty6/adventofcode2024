package dev.zooty.day11;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

@Getter
public class Stone {
    private static final List<StoneRule> ruleList = initRules();

    private long value;

    public Stone(String input) {
        value = Long.parseLong(input);
    }

    public long blinkAmount(int amount) {
        return blink(amount, new HashMap<>());
    }

    private long blink(int amount, Map<StoneState, Long> cache) {
        if (amount == 1) {
            return getDigits(value) % 2 == 0 ? 2 : 1;
        }
        StoneState stoneState = new StoneState(value, amount);
        if (cache.containsKey(stoneState)) {
            return cache.get(stoneState);
        }

        long result = ruleList.stream()
                .filter(stoneRule -> stoneRule.rule().test(value))
                .findFirst()
                .map(rule -> rule.change().apply(this))
                .orElseThrow()
                .map(newStone -> newStone.blink(amount - 1, cache) + this.blink(amount - 1, cache))
                .orElse(this.blink(amount - 1, cache));

        cache.put(stoneState, result);
        return result;
    }

    private static int getDigits(Long value) {
        long pow = 10;
        int digitCount = 1;
        while (pow <= value) {
            pow *= 10;
            digitCount++;
        }
        return digitCount;
    }

    private static List<StoneRule> initRules() {
        return List.of(
                new StoneRule(aLong -> aLong == 0L, stone -> {
                    stone.value++;
                    return Optional.empty();
                }),
                new StoneRule(aLong -> getDigits(aLong) % 2 == 0, stone -> {
                    String valueString = "" + stone.value;
                    int halfLength = valueString.length() / 2;
                    String endOfString = valueString.substring(halfLength);
                    stone.value = Long.parseLong(valueString.substring(0, halfLength));
                    return Optional.of(new Stone(endOfString));
                }),
                new StoneRule(aLong -> getDigits(aLong) % 2 != 0, stone -> {
                    stone.value *= 2024;
                    return Optional.empty();
                }));
    }

    private record StoneRule(Predicate<Long> rule, Function<Stone, Optional<Stone>> change) {
    }
    private record StoneState(Long value, int blinkAmount) {
    }
}
