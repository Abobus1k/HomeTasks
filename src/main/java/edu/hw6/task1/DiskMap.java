package edu.hw6.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class DiskMap implements Map<String, String> {

    private final String filePath;

    public DiskMap(String filePath) {
        this.filePath = filePath;

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return readFromFile().size();
    }

    @Override
    public boolean isEmpty() {
        return readFromFile().isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return readFromFile().containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return readFromFile().containsValue(value);
    }

    @Override
    public String get(Object key) {
        return readFromFile().get(key);
    }

    @Override
    public String put(String key, String value) {
        Map<String, String> data = readFromFile();
        String oldValue = data.put(key, value);
        writeToFile(data);
        return oldValue;
    }

    @Override
    public String remove(Object key) {
        Map<String, String> data = readFromFile();
        String removedValue = data.remove(key);
        writeToFile(data);
        return removedValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        Map<String, String> data = readFromFile();
        data.putAll(m);
        writeToFile(data);
    }

    @Override
    public void clear() {
        writeToFile(new HashMap<>());
    }

    @Override
    public @NotNull Set<String> keySet() {
        return readFromFile().keySet();
    }

    @Override
    public @NotNull Collection<String> values() {
        return readFromFile().values();
    }

    @Override
    public @NotNull Set<Entry<String, String>> entrySet() {
        return readFromFile().entrySet();
    }

    private Map<String, String> readFromFile() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return reader.lines()
                .map(line -> line.split(":", 2))
                .filter(parts -> parts.length == 2)
                .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeToFile(Map<String, String> data) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            data.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .forEach(line -> {
                    try {
                        writer.write(line);
                        writer.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
