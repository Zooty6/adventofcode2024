package dev.zooty.day3;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Instruction {
    private final int operand1;
    private final int operand2;

    public int calculate() {
        return operand1 * operand2;
    }
}
