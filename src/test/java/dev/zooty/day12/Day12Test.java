package dev.zooty.day12;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class Day12Test {

    @Test
    void firstExampleP1() {
        Day12 day12 = new Day12() {
            @Override
            public BufferedReader getInputReader() {
                return new BufferedReader(new StringReader("""
                        AAAA
                        BBCD
                        BBCC
                        EEEC
                        """));
            }
        };
        assertEquals("140", day12.getSolution1());
    }

    @Test
    void secondExampleP1() {
        Day12 day12 = new Day12() {
            @Override
            public BufferedReader getInputReader() {
                return new BufferedReader(new StringReader("""
                        OOOOO
                        OXOXO
                        OOOOO
                        OXOXO
                        OOOOO
                        """));
            }
        };
        assertEquals("772", day12.getSolution1());
    }


    @Test
    void getSolution1() {
        assertEquals("1930", new Day12().getSolution1());
    }

    @Test
    void firstExampleP2() {
        Day12 day12 = new Day12() {
            @Override
            public BufferedReader getInputReader() {
                return new BufferedReader(new StringReader("""
                        AAAA
                        BBCD
                        BBCC
                        EEEC
                        """));
            }
        };
        assertEquals("80", day12.getSolution2());
    }

    @Test
    void secondExampleP2() {
        Day12 day12 = new Day12() {
            @Override
            public BufferedReader getInputReader() {
                return new BufferedReader(new StringReader("""
                        OOOOO
                        OXOXO
                        OOOOO
                        OXOXO
                        OOOOO
                        """));
            }
        };
        assertEquals("436", day12.getSolution2());
    }

    @Test
    void thirdExampleP2() {
        Day12 day12 = new Day12() {
            @Override
            public BufferedReader getInputReader() {
                return new BufferedReader(new StringReader("""
                        EEEEE
                        EXXXX
                        EEEEE
                        EXXXX
                        EEEEE
                        """));
            }
        };
        assertEquals("236", day12.getSolution2());
    }

    @Test
    void forthExampleP2() {
        Day12 day12 = new Day12() {
            @Override
            public BufferedReader getInputReader() {
                return new BufferedReader(new StringReader("""
                        AAAAAA
                        AAABBA
                        AAABBA
                        ABBAAA
                        ABBAAA
                        AAAAAA
                        """));
            }
        };
        assertEquals("368", day12.getSolution2());
    }

    @Test
    void getSolution2() {
        assertEquals("1206", new Day12().getSolution2());
    }
}