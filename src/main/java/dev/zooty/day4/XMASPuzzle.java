package dev.zooty.day4;

import java.io.BufferedReader;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class XMASPuzzle {
    private static final String XMAS_REGEX = "(XMAS|SAMX)";
    private static final Pattern XMAS_PATTERN = Pattern.compile(XMAS_REGEX);
    private static final String X_MAS_REGEX = "(MAS|SAM)";
    private static final Pattern X_MAS_PATTERN = Pattern.compile(X_MAS_REGEX);
    private final List<List<Character>> puzzle;

    public XMASPuzzle(BufferedReader reader) {
        puzzle = reader.lines()
                .map(line -> line
                        .chars()
                        .mapToObj(aCharacter -> (char) aCharacter)
                        .toList())
                .toList();
    }

    public int xmasCount() {
        return getHorizontalCount() + getVerticalCount() + getForwardDiagonalCount() + getBackwardDiagonalCount();
    }

    public int xDashMasCount() {
        return IntStream.range(1, puzzle.size() - 1)
                .map(lineIndex -> IntStream.range(1, puzzle.getFirst().size() - 1)
                        .map(columnIndex -> puzzle.get(lineIndex).get(columnIndex) == 'A' && XMASPuzzle.findWordInX(
                                "" + puzzle.get(lineIndex - 1).get(columnIndex - 1) + 'A' + puzzle.get(lineIndex + 1).get(columnIndex + 1),
                                "" + puzzle.get(lineIndex - 1).get(columnIndex + 1) + 'A' + puzzle.get(lineIndex + 1).get(columnIndex - 1))
                                ? 1 : 0)
                        .sum())
                .sum();
    }

    private int getHorizontalCount() {
        return puzzle.stream()
                .mapToInt(characters -> IntStream.range(0, characters.size() - 3)
                        .mapToObj(index -> "" + characters.get(index) + characters.get(index + 1) + characters.get(index + 2) + characters.get(index + 3))
                        .mapToInt(XMASPuzzle::findWordInPart)
                        .sum())
                .sum();
    }

    private int getVerticalCount() {
        return IntStream.range(0, puzzle.getFirst().size())
                .map(columnIndex -> IntStream.range(0, puzzle.size() - 3)
                        .mapToObj(lineIndex -> "" + puzzle.get(lineIndex).get(columnIndex)
                                + puzzle.get(lineIndex + 1).get(columnIndex)
                                + puzzle.get(lineIndex + 2).get(columnIndex)
                                + puzzle.get(lineIndex + 3).get(columnIndex))
                        .mapToInt(XMASPuzzle::findWordInPart)
                        .sum())
                .sum();
    }

    /*
    ...****
    ..*****
    .******
    *******
    ******.
    *****..
    ****...
    */
    private int getForwardDiagonalCount() {
        return IntStream.range(0, puzzle.size() - 3)
                .map(lineIndex -> IntStream.range(3, puzzle.getFirst().size())
                        .mapToObj(columnIndex -> "" + puzzle.get(lineIndex).get(columnIndex)
                                + puzzle.get(lineIndex + 1).get(columnIndex - 1)
                                + puzzle.get(lineIndex + 2).get(columnIndex - 2)
                                + puzzle.get(lineIndex + 3).get(columnIndex - 3))
                        .mapToInt(XMASPuzzle::findWordInPart)
                        .sum())
                .sum();
    }

    /*
    ****...
    *****..
    ******.
    *******
    .******
    ..*****
    ...****
    */
    private int getBackwardDiagonalCount() {
        return IntStream.range(0, puzzle.size() - 3)
                .map(lineIndex -> IntStream.range(0, puzzle.getFirst().size() - 3)
                        .mapToObj(columnIndex -> "" + puzzle.get(lineIndex).get(columnIndex)
                                + puzzle.get(lineIndex + 1).get(columnIndex + 1)
                                + puzzle.get(lineIndex + 2).get(columnIndex + 2)
                                + puzzle.get(lineIndex + 3).get(columnIndex + 3))
                        .mapToInt(XMASPuzzle::findWordInPart)
                        .sum())
                .sum();
    }

    private static int findWordInPart(String linePart) {
        return XMAS_PATTERN.matcher(linePart).find() ? 1 : 0;
    }

    private static boolean findWordInX(String word1, String word2) {
        return X_MAS_PATTERN.matcher(word1).find() && X_MAS_PATTERN.matcher(word2).find();
    }
}
