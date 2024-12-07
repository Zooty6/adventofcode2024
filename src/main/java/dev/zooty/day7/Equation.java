package dev.zooty.day7;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@ToString
public class Equation {
    @Getter
    private final long testValue;
    private final List<Long> operands;

    public Equation(String string) {
        var splitInput = string.split(":");
        testValue = Long.parseLong(splitInput[0].trim());
        operands = Stream.of(splitInput[1].trim().split(" "))
                .map(Long::parseLong)
                .toList();
    }

    public boolean test() {
        return getAllPossibleOperationPermutations(operands.size() - 1, List.of(Operator.ADDITION, Operator.MULTIPLY))
                .parallelStream()
                .anyMatch(this::testOnOperationPermutation);
    }

    public boolean testWithAllOperators() {
        return getAllPossibleOperationPermutations(operands.size() - 1, List.of(Operator.values()))
                .parallelStream()
                .anyMatch(this::testOnOperationPermutation);
    }

    private boolean testOnOperationPermutation(List<Operator> operatorPermutations) {
        long accumulated = operands.getFirst();
        int index = 1;
        while (accumulated <= testValue && index < operands.size()) {
            accumulated = operatorPermutations.get(index - 1).doOperation(accumulated, operands.get(index));
            index++;
        }
        return accumulated == testValue;
    }

    private static List<List<Operator>> getAllPossibleOperationPermutations(int amount, List<Operator> possibleOperations) {
        List<List<Operator>> result = new ArrayList<>();
        if(amount == 0) {
            result.add(new ArrayList<>());
            return result;
        }
        for(Operator operation : possibleOperations) {
            List<List<Operator>> subResult = getAllPossibleOperationPermutations(amount - 1, possibleOperations);
            for(List<Operator> subList : subResult) {
                subList.add(operation);
                result.add(subList);
            }
        }
        return result;
    }
}
