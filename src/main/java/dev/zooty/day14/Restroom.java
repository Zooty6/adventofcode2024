package dev.zooty.day14;

import dev.zooty.day6.Position;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Restroom {
    private final List<Robot> robots;
    private final int width;
    private final int height;

    public Restroom(BufferedReader reader, int width, int height) {
        this.width = width;
        this.height = height;
        robots = reader.lines()
                .map(Robot::new)
                .toList();
    }

    public void move(int steps) {
        robots.forEach(robot -> robot.move(steps, width, height));
    }

    public int getSafetyFactor() {
        return robots
                .stream()
                .collect(Collectors.groupingBy(robot ->
                                robot.getQuadrant(width, height),
                        Collectors.counting()))
                .entrySet()
                .stream()
                .filter(quadrantLongEntry -> !Robot.Quadrant.NONE.equals(quadrantLongEntry.getKey()))
                .mapToInt(quadrantLongEntry -> quadrantLongEntry.getValue().intValue())
                .reduce(1, (a, b) -> a * b);
    }

    public int getChristmasTreeCount() {
        int step = 0;
        while (!isATree()) {
            move(1);
            step++;
        }
        return step;
    }

    private boolean isATree() {
        return looksLikeATree()
                .map(yIndex -> robots.stream()
                        .filter(robot -> robot.getPosition().y() == yIndex)
                        .anyMatch(robot -> {
                            for (int i = 0; i < 30; i++) {
                                int finalI = i;
                                if (robots.stream().noneMatch(nextRobot -> nextRobot.getPosition().equals(new Position(robot.getPosition().x() + finalI, yIndex)))) {
                                    return false;
                                }
                            }
                            return true;
                        })
                ).orElse(false);
    }

    private Optional<Integer> looksLikeATree() {
        return robots
                .stream()
                .collect(Collectors
                        .groupingBy(robot -> robot.getPosition().y(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 32)
                .map(Map.Entry::getKey)
                .findAny();
    }

    @Override
    public String toString() {
        return IntStream.range(0, height)
                .mapToObj(lineIndex -> IntStream.range(0, width)
                        .mapToObj(columnIndex -> robots.stream().anyMatch(robot -> robot.getPosition().equals(new Position(columnIndex, lineIndex))) ? "#" : ".")
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }
}
