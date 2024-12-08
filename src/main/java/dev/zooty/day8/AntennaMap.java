package dev.zooty.day8;

import dev.zooty.day6.Position;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AntennaMap {
    /**
     * Antennas grouped by frequency
     */
    private final Map<Character, List<Antenna>> antennas;
    AtomicInteger maxX = new AtomicInteger(0);
    private Integer maxY = null;

    public AntennaMap(BufferedReader inputReader) {
        antennas = parseAntennas(inputReader);
    }

    public List<Position> getAntinodes(int antinodeCount) {
        return antennas.keySet().stream()
                .flatMap(character -> calculateAntinodes(antennas.get(character), antinodeCount))
                .distinct()
                .filter(this::insideMap)
                .toList();
    }

    private Stream<Position> calculateAntinodes(List<Antenna> antennas, int antinodeCount) {
        return getAllAntennaPairs(antennas)
                .stream()
                .flatMap(antennaPair -> calculateAntinodes(antennaPair, antinodeCount));
    }

    private Stream<Position> calculateAntinodes(AntennaPair antennaPair, int antinodeCount) {
        return Stream.concat(calculateAntinodes(antennaPair.antenna1, antennaPair.antenna2, antinodeCount),
                calculateAntinodes(antennaPair.antenna2, antennaPair.antenna1, antinodeCount));
    }

    private Stream<Position> calculateAntinodes(Antenna antennaFrom, Antenna antennaTo, int antinodeCount) {
        return IntStream.range(-1 * antinodeCount + 1, antinodeCount + 1)
                .filter(antinodeCountIndex -> antinodeCountIndex != 0)
                .mapToObj(antinodeCountIndex -> new Position(antennaTo.position().x() + antinodeCountIndex * (antennaTo.position().x() - antennaFrom.position().x()),
                        antennaTo.position().y() + antinodeCountIndex * (antennaTo.position().y() - antennaFrom.position().y())));
    }

    private List<AntennaPair> getAllAntennaPairs(List<Antenna> antennas) {
        List<AntennaPair> antennaPairs = new ArrayList<>();
        for (int i = 0; i < antennas.size(); i++) {
            for (int j = i + 1; j < antennas.size(); j++) {
                antennaPairs.add(new AntennaPair(antennas.get(i), antennas.get(j)));
            }
        }
        return antennaPairs;
    }

    private boolean insideMap(Position position) {
        return position.x() >= 0 && position.x() < maxX.get() && position.y() >= 0 && position.y() < maxY;
    }

    private Map<Character, List<Antenna>> parseAntennas(BufferedReader inputReader) {
        return inputReader.lines()
                .map(line -> new LineIndexer(maxX.getAndIncrement(), line))
                .flatMap(this::getAntennasFromLine)
                .collect(Collectors.groupingBy(Antenna::frequency));
    }

    private Stream<Antenna> getAntennasFromLine(LineIndexer indexer) {
        AtomicInteger charIndex = new AtomicInteger(0);
        if (maxY == null) {
            maxY = indexer.line().length();
        }
        return indexer.line()
                .chars()
                .mapToObj(character -> createAntenna(character, indexer.index(), charIndex))
                .filter(Objects::nonNull);
    }

    private Antenna createAntenna(int character, int lineIndex, AtomicInteger charIndex) {
        if (character != '.') {
            return new Antenna((char) character, new Position(lineIndex, charIndex.getAndIncrement()));
        } else {
            charIndex.incrementAndGet();
            return null;
        }
    }

    private record LineIndexer(int index, String line) {
    }

    private record AntennaPair(Antenna antenna1, Antenna antenna2) {
    }
}
