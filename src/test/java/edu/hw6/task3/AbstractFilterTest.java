package edu.hw6.task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractFilterTest {

    private static final Path TEST_DIR = Paths.get("src/test/java/edu/hw6/task3/testFiles");

    @Test
    void testFilterByAttributes() throws IOException {
        AbstractFilter filter = AbstractFilter.regularFile()
            .and(AbstractFilter.readable())
            .and(AbstractFilter.writeble());

        List<Path> filteredEntries = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            for (Path entry : entries) {
                filteredEntries.add(entry);
            }
        }

        assertEquals(4, filteredEntries.size());
    }

    @Test
    void testFilterBySize() throws IOException {
        AbstractFilter filter = AbstractFilter.largerThan(100);

        List<Path> filteredEntries = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            for (Path entry : entries) {
                filteredEntries.add(entry);
            }
        }

        assertEquals(1, filteredEntries.size());
    }

    @Test
    void testFilterByGlob() throws IOException {
        AbstractFilter filter = AbstractFilter.globMatches("*.txt");

        List<Path> filteredEntries = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            for (Path entry : entries) {
                filteredEntries.add(entry);
            }
        }

        assertEquals(2, filteredEntries.size());
    }


    @Test
    void testFilterByName() throws IOException {
        AbstractFilter filter = AbstractFilter.regexMatches("test");

        List<Path> filteredEntries = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            for (Path entry : entries) {
                filteredEntries.add(entry);
            }
        }

        assertEquals(3, filteredEntries.size());
    }

    @Test
    void testFilterByMagic() throws IOException {
        AbstractFilter filter = AbstractFilter.magicNumber(0x89, 'P', 'N', 'G');

        List<Path> filteredEntries = new ArrayList<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(TEST_DIR, filter)) {
            for (Path entry : entries) {
                filteredEntries.add(entry);
            }
        }

        assertEquals(1, filteredEntries.size());
    }
}
