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

    public synchronized long load() throws IOException {
        try (Stream<String> lines = Files.lines(STORAGE_PATH)) {
            return lines.map(Long::valueOf).reduce(0L, Long::sum);
        }
    }

    public synchronized void save(long amount) throws IOException {
        byte[] data = String.format("%d\n", amount).getBytes();
        Files.write(STORAGE_PATH, data, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
