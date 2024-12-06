package dev.zooty.day6;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public final class Empty implements MapUnit {
    private final char initialCharacter;
    @Setter
    private boolean isVisited = false;
    private final Set<Direction> visitedDirections = HashSet.newHashSet(4);

    @Override
    public MapUnit copy() {
        return new Empty(initialCharacter);
    }

    public void addVisitedDirection(Direction direction) {
        visitedDirections.add(direction);
    }
}


