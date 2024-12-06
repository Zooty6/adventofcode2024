package dev.zooty.day6;

public sealed interface MapUnit permits Empty, Obstruction {
    char getInitialCharacter();
    boolean isVisited();
    MapUnit copy();
}
