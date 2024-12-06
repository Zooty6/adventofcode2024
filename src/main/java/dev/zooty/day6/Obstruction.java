package dev.zooty.day6;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class Obstruction implements MapUnit {
    private final char initialCharacter;
    private final boolean isVisited = false;

    @Override
    public MapUnit copy() {
        return new Obstruction(initialCharacter);
    }
}
