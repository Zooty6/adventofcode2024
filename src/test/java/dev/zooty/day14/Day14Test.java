package dev.zooty.day14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day14Test {

    @Test
    void getSolution1() {
        assertEquals("12", new Day14(){
            @Override
            public int getWidth() {
                return 11;
            }

            @Override
            public int getHeight() {
                return 7;
            }
        }.getSolution1());
    }

    // Part 2 is untestable
}