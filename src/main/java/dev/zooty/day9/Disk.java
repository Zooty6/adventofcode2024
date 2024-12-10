package dev.zooty.day9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class Disk {
    private final List<Block> diskBlocks = new ArrayList<>();

    public Disk(String input) {
        AtomicInteger fileId = new AtomicInteger();
        AtomicBoolean isFile = new AtomicBoolean(true);
        input.chars().forEach(blockLength -> parseBlock(fileId, isFile, blockLength));
    }

    public void doDeFragment() {
        while(isFragmented()) {
            var blockToMove = diskBlocks.stream()
                    .filter(File.class::isInstance)
                    .reduce((block, block2) -> block2)
                    .orElseThrow();
            diskBlocks.remove(blockToMove);
            var firstFreeSpace = IntStream.range(1, diskBlocks.size())
                    .filter(index -> diskBlocks.get(index) instanceof Free)
                    .findFirst()
                    .orElseThrow();
            diskBlocks.set(firstFreeSpace, blockToMove);
        }
    }

    private boolean isFragmented() {
        return IntStream.range(1, diskBlocks.size())
                .parallel()
                .anyMatch(index -> diskBlocks.get(index) instanceof File && diskBlocks.get(index -1) instanceof Free);

    }

    public long getChecksum() {
        return IntStream.range(0, diskBlocks.size())
                .filter(index -> diskBlocks.get(index) instanceof File)
                .mapToLong(index -> (long) index * ((File) diskBlocks.get(index)).getId())
                .sum();
    }

    private void parseBlock(AtomicInteger fileId, AtomicBoolean isFile, int blockLength) {
        final int id = fileId.get();
        addBlock(blockLength - '0', isFile.get() ? () -> new File(id) : Free::new);
        if (isFile.get()) {
            fileId.incrementAndGet();
        }
        isFile.set(!isFile.get());
    }

    private void addBlock(int amount, Supplier<Block> blockSupplier) {
        for (var i = 0; i < amount; i++) {
            diskBlocks.add(blockSupplier.get());
        }
    }
}
