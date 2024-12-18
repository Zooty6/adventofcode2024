package dev.zooty.day18;

import dev.zooty.day6.Position;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Memory {
    private final int size;
    private final List<FallingByte> bytes;

    public Memory(BufferedReader reader, int size) {
        this.size = size;
        bytes = reader.lines()
                .map(FallingByte::new)
                .toList();
    }

    public int getShortestRoute(int timeSpent) {
        var positionPositionMap = traverseMap(timeSpent);
        int count = 0;
        var current = new Position(size, size);
        while (current.x() != 0 || current.y() != 0) {
            current = positionPositionMap.get(current);
            count++;
        }
        return count;
    }

    public String getTimeBeforeStuck(int timeSpent) {
        int begin = timeSpent;
        int end = bytes.size();
        while (begin + 1 < end) {
            int mid = (begin + end) / 2;
            if (isDead(mid)) {
                end = mid;
            } else {
                begin = mid;
            }
        }
        return String.valueOf(bytes.get(begin));
    }

    private boolean isDead(int timeSpent) {
        try {
            traverseMap(timeSpent);
        } catch (IllegalStateException ignored) {
            return true;
        }
        return false;
    }

    private Map<Position, Position> traverseMap(int timeSpent) {
        var queue = new LinkedList<>(List.of(new Position(0, 0)));
        var visited = new HashSet<Position>();
        var backwardPointer = new HashMap<Position, Position>();
        while (!queue.isEmpty()) {
            var current = queue.remove();
            var neighbors = getNeighbors(current, timeSpent);
            if (isExit(current)) {
                return backwardPointer;
            }
            for (var neighbor : neighbors) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                backwardPointer.put(neighbor, current);
                visited.add(neighbor);
                if (isExit(neighbor)) {
                    return backwardPointer;
                }
                queue.add(neighbor);
            }
        }

        throw new IllegalStateException("No path found");
    }

    private boolean isExit(Position current) {
        return current.x() == size && current.y() == size;
    }

    private List<Position> getNeighbors(Position current, int fallenByteAmount) {
        return Stream.of(current.down(), current.right(), current.left(), current.up())
                .filter(this::inBoundary)
                .filter(position -> notCrushed(position, fallenByteAmount))
                .toList();
    }

    private boolean notCrushed(Position position, int fallenByteAmount) {
        return bytes.stream()
                .limit(fallenByteAmount)
                .noneMatch(fallingByte -> position.equals(fallingByte.getPosition()));
    }

    private boolean inBoundary(Position position) {
        return position.x() >= 0 && position.x() <= size && position.y() >= 0 && position.y() <= size;
    }

    @Override
    public String toString() {
        return IntStream.range(0, size + 1)
                .mapToObj(line -> IntStream.range(0, size + 1)
                        .mapToObj(column -> bytes
                                .stream()
                                .limit(1024)
                                .anyMatch(fallingByte -> fallingByte.getPosition().equals(new Position(line, column))) ? "#" : ".")
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }
}
