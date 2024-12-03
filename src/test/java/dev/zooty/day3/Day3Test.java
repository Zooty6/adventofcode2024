package dev.zooty.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day3Test {

    @Test
    void getSolution1() {
        assertEquals("161", new Day3().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("48", new Day3().getSolution2());
    }
}