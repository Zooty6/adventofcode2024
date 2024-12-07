package dev.zooty.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    @Test
    void getSolution1() {
        assertEquals("3749", new Day7().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("11387", new Day7().getSolution2());
    }
}