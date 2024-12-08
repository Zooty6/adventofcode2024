package dev.zooty.day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day8Test {

    @Test
    void getSolution1() {
        assertEquals("14", new Day8().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("34", new Day8().getSolution2());
    }
}