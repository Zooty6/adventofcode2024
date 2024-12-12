package dev.zooty.day10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    @Test
    void getSolution1() {
        assertEquals("36", new Day10().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("81", new Day10().getSolution2());
    }
}