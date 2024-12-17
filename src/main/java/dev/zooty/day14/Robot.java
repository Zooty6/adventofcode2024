package dev.zooty.day14;

import dev.zooty.day6.Position;
import lombok.Getter;

import java.util.regex.Pattern;

public class Robot {
    private static final String REGEX = "p=(?<px>-?\\d+),(?<py>-?\\d+) v=(?<vx>-?\\d+),(?<vy>-?\\d+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);
    @Getter
    private Position position;
    private Position velocity;

    public Robot(String input) {
        PATTERN.matcher(input).results().findAny().ifPresentOrElse(matchResult -> {
            this.position = new Position(Integer.parseInt(matchResult.group("px")), Integer.parseInt(matchResult.group("py")));
            this.velocity = new Position(Integer.parseInt(matchResult.group("vx")), Integer.parseInt(matchResult.group("vy")));
        }, () -> { throw new IllegalArgumentException("Invalid input"); });
    }

    public Quadrant getQuadrant(int width, int height) {
        return switch (position) {
            case Position currentPos when currentPos.x() < width / 2 && currentPos.y() < height / 2 -> Quadrant.TOP_LEFT;
            case Position currentPos when currentPos.x() < width / 2 && currentPos.y() > height / 2 -> Quadrant.BOTTOM_LEFT;
            case Position currentPos when currentPos.x() > width / 2 && currentPos.y() < height / 2 -> Quadrant.TOP_RIGHT;
            case Position currentPos when currentPos.x() > width / 2 && currentPos.y() > height / 2 -> Quadrant.BOTTOM_RIGHT;
            default -> Quadrant.NONE;
        };
    }

    public void move(int steps, int width, int height) {
        int x = position.x();
        int y = position.y();
        for (int i = 0; i < steps; i++) {
            x += velocity.x();
            y += velocity.y();
            x = x < 0 ? x + width : x % width;
            y = y < 0 ? y + height : y % height;
        }
        position = new Position(x, y);
    }

    public enum Quadrant {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        NONE
    }
}
