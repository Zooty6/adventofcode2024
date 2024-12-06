package dev.zooty.day6;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public Direction turnRight() {
        return switch (this) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }

    public Position getNextPosition(Position position) {
        return switch (this) {
            case UP -> new Position(position.x() -1, position.y());
            case RIGHT -> new Position(position.x(), position.y() + 1);
            case DOWN -> new Position(position.x() + 1, position.y());
            case LEFT -> new Position(position.x(), position.y() - 1);
        };
    }
}
