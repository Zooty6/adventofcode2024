package dev.zooty.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    @Test
    void getSolution1() {
        assertEquals("18", new Day4().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("9", new Day4().getSolution2());
    }
}