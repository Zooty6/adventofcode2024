package dev.zooty.day6;

public record Position(int x, int y) {
    public Position up() {
        return new Position(x() - 1, y());
    }

    public Position down() {
        return new Position(x() + 1, y());
    }

    public Position left() {
        return new Position(x(), y() - 1);
    }

    public Position right() {
        return new Position(x(), y() + 1);
    }
}
