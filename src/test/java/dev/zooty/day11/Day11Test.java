package dev.zooty.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day11Test {

    @Test
    void getSolution1() {
        assertEquals("55312", new Day11().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("65601038650482", new Day11().getSolution2());
    }
}