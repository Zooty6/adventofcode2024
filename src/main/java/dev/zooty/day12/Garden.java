package dev.zooty.day12;

import dev.zooty.day6.Position;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Garden {
    private final List<List<Plot>> plots;

    public Garden(BufferedReader reader) {
        plots = reader.lines()
                .map(line -> line.chars()
                        .mapToObj(character -> new Plot((char) character))
                        .toList())
                .toList();
    }

    public void mapPlotRegions() {
        IntStream.range(0, plots.size())
                .forEach(lineIndex -> IntStream.range(0, plots.getFirst().size())
                        .forEach(columnIndex -> plots.get(lineIndex).get(columnIndex).mapRegion(plots, new Position(lineIndex, columnIndex))));
    }

    public int getPrice() {
        HashSet<Plot> calculatedPlotAreas = new HashSet<>();
        HashSet<Plot> calculatedPlotPerimeters = new HashSet<>();
        return plots.stream()
                .flatMap(Collection::stream)
                .filter(plot -> !calculatedPlotAreas.contains(plot))
                .mapToInt(plot -> plot.getAreaOfRegion(calculatedPlotAreas) * plot.getPerimeterOfRegion(calculatedPlotPerimeters))
                .sum();
    }

    public int getDiscountedPrice() {
        HashSet<Plot> calculatedPlotAreas = new HashSet<>();
        HashSet<Plot> calculatedPlotSides = new HashSet<>();
        return plots.stream()
                .flatMap(Collection::stream)
                .filter(plot -> !calculatedPlotAreas.contains(plot))
                .mapToInt(plot -> plot.getAreaOfRegion(calculatedPlotAreas) * plot.getSidesOfRegion(calculatedPlotSides))
                .sum();
    }
}
