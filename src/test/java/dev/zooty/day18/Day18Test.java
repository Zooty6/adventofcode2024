package dev.zooty.day18;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day18Test {

    @Test
    void getSolution1() {
        assertEquals("22", new Day18() {
            @Override
            public int getMemorySize() {
                return 6;
            }

            @Override
            public int getTimeSpentForFirstSolution() {
                return 12;
            }
        }.getSolution1());
    }

    @Test
    void getSolution2() {
        assertEquals("6,1", new Day18() {
            @Override
            public int getMemorySize() {
                return 6;
            }

            @Override
            public int getTimeSpentForFirstSolution() {
                return 12;
            }
        }.getSolution2());
    }
}