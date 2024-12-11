package dev.zooty.day9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    @Test
    void getSolution1() {
        assertEquals("1928", new Day9().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("2858", new Day9().getSolution2());
    }
}