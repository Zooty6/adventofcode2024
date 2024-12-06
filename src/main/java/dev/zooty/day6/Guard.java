package dev.zooty.day6;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static dev.zooty.day6.Direction.DOWN;
import static dev.zooty.day6.Direction.LEFT;
import static dev.zooty.day6.Direction.RIGHT;
import static dev.zooty.day6.Direction.UP;

@Getter
@Setter
@NoArgsConstructor
public final class Guard {
    private Direction direction;
    private Direction initialDirection;
    private Position position;
    private Position initialPosition;

    public Guard(char initCharacter) {
        direction = switch (initCharacter) {
            case '^' -> UP;
            case '>' -> RIGHT;
            case 'v' -> DOWN;
            case '<' -> LEFT;
            default -> throw new IllegalArgumentException("Invalid character");
        };
        initialDirection = direction;
    }

    public Position getNextStep() {
        return direction.getNextPosition(position);
    }

    public Guard createNew() {
        var copy = new Guard();
        copy.direction = initialDirection;
        copy.initialDirection = initialDirection;
        copy.position = initialPosition;
        copy.initialPosition = initialPosition;
        return copy;
    }
}
