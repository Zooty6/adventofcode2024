package dev.zooty.day7;

import lombok.RequiredArgsConstructor;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public enum Operator {
    MULTIPLY((a, b) -> a * b),
    ADDITION(Long::sum),
    CONCATENATION((a, b) -> {
        long pow = 10;
        while (pow <= b) {
            pow *= 10;
        }
        return a * pow + b;
    });

    private final BiFunction<Long, Long, Long> operation;

    public Long doOperation(Long a, Long b) {
        return operation.apply(a, b);
    }
}
