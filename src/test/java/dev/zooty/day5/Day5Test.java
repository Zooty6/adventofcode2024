package dev.zooty.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    @Test
    void getSolution1() {
        assertEquals("143", new Day5().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("123", new Day5().getSolution2());
    }
}