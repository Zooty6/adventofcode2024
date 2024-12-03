package dev.zooty.day3;

import java.io.BufferedReader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Program {
    private static final String CONDITION_REGEX = "don't\\(\\).*?do\\(\\)";
    private static final String INSTRUCTION_REGEX = "mul\\((?<operand1>\\d{1,3}),(?<operand2>\\d{1,3})\\)"; // NOSONAR false positive
    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile(INSTRUCTION_REGEX);
    private final List<Instruction> instructions;

    public Program(BufferedReader inputReader, boolean handleConditions) {
        var oneLineInput = inputReader.lines().collect(Collectors.joining());
        oneLineInput = handleConditions ? oneLineInput.replaceAll(CONDITION_REGEX, "") : oneLineInput;
        instructions = INSTRUCTION_PATTERN.matcher(oneLineInput).results()
                .map(matchResult -> new Instruction(
                        Integer.parseInt(matchResult.group("operand1")),
                        Integer.parseInt(matchResult.group("operand2"))))
                .toList();
    }

    public int calculate() {
        return instructions.stream()
                .mapToInt(Instruction::calculate)
                .sum();
    }
}
