package ista.tests;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class FiscalStorage {
    private static final String STORAGE_DIR = System.getProperty("user.home", "/data");
    private static final Path STORAGE_PATH = FileSystems.getDefault().getPath(STORAGE_DIR, "storage.dat");

    public FiscalStorage() throws IOException {
        if (Files.notExists(STORAGE_PATH)) {
            Files.createFile(STORAGE_PATH);
        }
    }

    public synchronized void reset() throws IOException {
        Files.delete(STORAGE_PATH);
        Files.createFile(STORAGE_PATH);
    }

    public synchronized float load() throws IOException {
        try (Stream<String> lines = Files.lines(STORAGE_PATH)) {
            return lines.map(Float::valueOf).reduce(0f, Float::sum);
        }
    }

    public synchronized void save(float amount) throws IOException {
        byte[] data = String.format("%f\n", amount).getBytes();
        Files.write(STORAGE_PATH, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
