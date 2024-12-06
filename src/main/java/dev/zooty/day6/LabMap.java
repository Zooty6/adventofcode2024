package dev.zooty.day6;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LabMap {
    private final List<List<MapUnit>> map;
    private List<Position> visitedPositions = new ArrayList<>();
    private Guard guard = null;

    public LabMap(BufferedReader reader) {
        map = reader.lines()
                .map(line -> line
                        .chars()
                        .mapToObj(aCharacter -> switch (aCharacter) {
                            case '#' -> new Obstruction((char) aCharacter);
                            case '.' -> new Empty((char) aCharacter);
                            default -> createGuard((char) aCharacter);
                        }).toList())
                .toList();
        guard.setPosition(findGuard());
        guard.setInitialPosition(guard.getPosition());
    }

    public int getPathLength() {
        traverse();
        return (int) map.stream()
                .mapToLong(line -> line.stream()
                        .filter(MapUnit::isVisited)
                        .count())
                .sum();
    }

    public int getNumberOfPossibleNewObstructions() {
        traverse();
        return (int) visitedPositions.stream()
                .map(this::isLoopingWithNewObstruction)
                .filter(looping -> looping)
                .count();
    }

    private boolean isLoopingWithNewObstruction(Position newObstructionPosition) {
        var mapCopy = getMapCopy();
        var guardCopy = guard.createNew();
        mapCopy.get(newObstructionPosition.x()).set(newObstructionPosition.y(), new Obstruction('O'));
        while (!wouldLeave(mapCopy, guardCopy)) {
            Position nextStep = guardCopy.getNextStep();
            switch (getUnitAt(nextStep, mapCopy)) {
                case Empty emptyUnit -> {
                    guardCopy.setPosition(nextStep);
                    if (emptyUnit.getVisitedDirections().contains(guardCopy.getDirection())) {
                        return true;
                    }
                    emptyUnit.setVisited(true);
                    emptyUnit.addVisitedDirection(guardCopy.getDirection());
                }
                case Obstruction ignored -> guardCopy.setDirection(guardCopy.getDirection().turnRight());
            }
        }
        return false;
    }

    private List<List<MapUnit>> getMapCopy() {
        return map.stream()
                .map(mapUnits -> mapUnits.stream()
                        .map(MapUnit::copy)
                        .collect(Collectors.toCollection(ArrayList::new)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void traverse() {
        while (!wouldLeave()) {
            Position nextStep = guard.getNextStep();
            switch (getUnitAt(nextStep)) {
                case Empty emptyUnit -> {
                    guard.setPosition(nextStep);
                    emptyUnit.setVisited(true);
                    emptyUnit.addVisitedDirection(guard.getDirection());
                    visitedPositions.add(guard.getPosition());
                }
                case Obstruction ignored -> guard.setDirection(guard.getDirection().turnRight());
            }
        }
        visitedPositions.remove(guard.getInitialPosition());
        visitedPositions = visitedPositions.stream().distinct().toList();
    }

    private boolean wouldLeave() {
        return wouldLeave(map, guard);
    }

    private boolean wouldLeave(List<List<MapUnit>> mapToCheck, Guard guardToCheck) {
        var nextStep = guardToCheck.getNextStep();
        return nextStep.x() < 0
                || nextStep.x() >= mapToCheck.size()
                || nextStep.y() < 0
                || nextStep.y() >= mapToCheck.getFirst().size();
    }

    private MapUnit getUnitAt(Position nextStep) {
        return getUnitAt(nextStep, map);
    }

    private MapUnit getUnitAt(Position nextStep, List<List<MapUnit>> map) {
        return map.get(nextStep.x()).get(nextStep.y());
    }

    private Empty createGuard(char aCharacter) {
        guard = new Guard(aCharacter);
        var emptyUnit = new Empty(aCharacter);
        emptyUnit.setVisited(true);
        return emptyUnit;
    }

    private Position findGuard() {
        return IntStream.range(0, map.size())
                .mapToObj(x -> IntStream.range(0, map.getFirst().size())
                        .filter(y -> List.of('^', '>', 'ˇ', '<').contains(map.get(x).get(y).getInitialCharacter()))
                        .mapToObj(y -> new Position(x, y))
                        .findAny())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return map.stream()
                .map(mapUnits -> mapUnits.stream()
                        .map(unit -> unit.isVisited() ? "X" : "" + unit.getInitialCharacter())
                        .collect(Collectors.joining()))
                .collect(Collectors.joining("\n"));
    }
}
