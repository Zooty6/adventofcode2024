package dev.zooty.day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    @Test
    void getSolution1() {
        assertEquals("41", new Day6().getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("6", new Day6().getSolution2());
    }
}