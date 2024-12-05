package dev.zooty.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Update {
    private final List<Integer> pages;

    public Update(String input) {
        pages = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int getMiddlePageNumber() {
        return pages.get(pages.size() / 2);
    }

    public boolean correct(List<OrderingRule> rules) {
        return rules.stream()
                .allMatch(this::correctForRule);
    }

    public Update fixWithRuleset(List<OrderingRule> rules) {
        do {
            rules.stream()
                    .filter(rule -> !correctForRule(rule))
                    .forEach(this::fixRuleOnList);
        } while (!correct(rules));
        return this;
    }

    private void fixRuleOnList(OrderingRule rule) {
        var firstPagePosition = pages.indexOf(rule.getFirstPage());
        var secondPagePosition = pages.indexOf(rule.getSecondPage());

        pages.set(firstPagePosition, rule.getSecondPage());
        pages.set(secondPagePosition, rule.getFirstPage());
    }

    private boolean correctForRule(OrderingRule rule) {
        var firstPagePosition = pages.indexOf(rule.getFirstPage());
        var secondPagePosition = pages.indexOf(rule.getSecondPage());

        if(firstPagePosition == -1 || secondPagePosition == -1) {
            return true;
        }
        return firstPagePosition < secondPagePosition;
    }
}