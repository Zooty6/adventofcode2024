package dev.zooty.day5;

import lombok.Getter;

@Getter
public class OrderingRule {
    private final int firstPage;
    private final int secondPage;

    public OrderingRule(String input) {
        String[] split = input.split("\\|");
        firstPage = Integer.parseInt(split[0]);
        secondPage = Integer.parseInt(split[1]);
    }
}
