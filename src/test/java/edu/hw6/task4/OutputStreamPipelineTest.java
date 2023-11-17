package edu.hw6.task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutputStreamPipelineTest {

    private static final Path TEST_FILE_PATH = Paths.get("src/test/java/edu/hw6/task4/testFiles/test.txt");

    @BeforeEach
    void setUp() {
        createTestFile();
    }

    @AfterEach
    void clearDirectory() {
        deleteTestFiles();
    }

    @Test
    void testWriteTextToFile() throws IOException {

        OutputStreamPipeline.writeTextToFile(TEST_FILE_PATH);

        try (BufferedReader reader = Files.newBufferedReader(TEST_FILE_PATH)) {
            String line = reader.readLine();
            assertEquals("Programming is learned by writing programs. ― Brian Kernighan", line);
        }

        long firstChecksum = computeAdler32Checksum();

        clearDirectory();

        setUp();

        OutputStreamPipeline.writeTextToFile(TEST_FILE_PATH);

        try (BufferedReader reader = Files.newBufferedReader(TEST_FILE_PATH)) {
            String line = reader.readLine();
        }

        long secondChecksum = computeAdler32Checksum();

        assertEquals(firstChecksum, secondChecksum);
    }

    private void createTestFile() {
        try {
            Path filePath = Paths.get(TEST_FILE_PATH.toUri());
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteTestFiles() {
        Path directoryPath = Paths.get("src/test/java/edu/hw6/task4/testFiles");

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
            for (Path file : directoryStream) {
                Files.delete(file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error deleting test files", e);
        }
    }

    private long computeAdler32Checksum() throws IOException {
        try (CheckedInputStream checkedInputStream = new CheckedInputStream(Files.newInputStream(
            OutputStreamPipelineTest.TEST_FILE_PATH), new Adler32())) {
            byte[] buffer = new byte[8192];
            while (checkedInputStream.read(buffer) >= 0) {
            }
            return checkedInputStream.getChecksum().getValue();
        }
    }
}
