package dev.zooty.day18;

import dev.zooty.day6.Position;
import lombok.Getter;

@Getter
public class FallingByte {
    private final Position position;

    public FallingByte(String input) {
        var parts = input.split(",");
        this.position = new Position(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    @Override
    public String toString() {
        return "%d,%d".formatted(position.x(), position.y());
    }
}
