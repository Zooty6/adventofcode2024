package dev.zooty.day9;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class File implements Block {
    private final int id;
}
