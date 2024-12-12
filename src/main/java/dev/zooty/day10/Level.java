package dev.zooty.day10;

import lombok.Getter;

@Getter
public class Level {
    private final int height;

    public Level(char digitChar) {
        height = digitChar - '0';
    }
}
