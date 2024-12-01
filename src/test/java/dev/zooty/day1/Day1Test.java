package dev.zooty.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    @Test
    void getSolution1() {
        assertEquals("11", new Day1().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("31", new Day1().getSolution2());
    }
}