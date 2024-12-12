package dev.zooty.day10;

import dev.zooty.day6.Position;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TopographicMap {
    private final List<List<Level>> map;

    public TopographicMap(BufferedReader input) {
        map = input.lines()
                .map(line -> line.chars()
                        .mapToObj(c -> new Level((char) c))
                        .toList())
                .toList();
    }

    public int countTrailHeadScores() {
        return findAllTrailHeads()
                .mapToInt(this::calculateScore)
                .sum();
    }

    public int countTrailHeadRatings() {
        return findAllTrailHeads()
                .mapToInt(this::calculateRating)
                .sum();
    }

    private int calculateRating(Position position) {
        return calculateRating(position, 0);
    }

    private int calculateScore(Position position) {
        var peaks = new HashSet<Position>();
        calculateScore(position, peaks);
        return peaks.size();
    }

    private void calculateScore(Position position, HashSet<Position> peaks) {
        if (getLevel(position).getHeight() == 9) {
            peaks.add(position);
        }
        if (isInBoundary(position.up()) && isUphill(getLevel(position.up()), getLevel(position))) {
            calculateScore(position.up(), peaks);
        }
        if (isInBoundary(position.down()) && isUphill(getLevel(position.down()), getLevel(position))) {
            calculateScore(position.down(), peaks);
        }
        if (isInBoundary(position.left()) && isUphill(getLevel(position.left()), getLevel(position))) {
            calculateScore(position.left(), peaks);
        }
        if (isInBoundary(position.right()) && isUphill(getLevel(position.right()), getLevel(position))) {
            calculateScore(position.right(), peaks);
        }
    }

    private int calculateRating(Position position, int i) {
        if (getLevel(position).getHeight() == 9) {
            i++;
        }
        if (isInBoundary(position.up()) && isUphill(getLevel(position.up()), getLevel(position))) {
            i = calculateRating(position.up(), i);
        }
        if (isInBoundary(position.down()) && isUphill(getLevel(position.down()), getLevel(position))) {
            i = calculateRating(position.down(), i);
        }
        if (isInBoundary(position.left()) && isUphill(getLevel(position.left()), getLevel(position))) {
            i = calculateRating(position.left(), i);
        }
        if (isInBoundary(position.right()) && isUphill(getLevel(position.right()), getLevel(position))) {
            i = calculateRating(position.right(), i);
        }
        return i;
    }

    private boolean isUphill(Level nextLevel, Level currentLevel) {
        return nextLevel.getHeight() == currentLevel.getHeight() + 1;
    }

    private boolean isInBoundary(Position nextPosition) {
        return nextPosition.x() >= 0 && nextPosition.x() < map.size()
                && nextPosition.y() >= 0 && nextPosition.y() < map.getFirst().size();
    }

    private Level getLevel(Position position) {
        return map.get(position.x()).get(position.y());
    }

    private Stream<Position> findAllTrailHeads() {
        return IntStream.range(0, map.size())
                .boxed()
                .flatMap(x -> IntStream.range(0, map.getFirst().size())
                        .filter(y -> map.get(x).get(y).getHeight() == 0)
                        .mapToObj(y -> new Position(x, y)));
    }
}
