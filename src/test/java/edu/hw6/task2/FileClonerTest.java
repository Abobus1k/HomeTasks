package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileClonerTest {

    private static final String TEST_FILE_PATH = "src/test/java/edu/hw6/task2/testFiles/test_file.txt";

    @BeforeEach
    void setUp() {
        createTestFile();
    }

    @AfterEach
    void clearDirectory() {
        deleteTestFiles();
    }

    @Test
    void testCloneFile() {
        Path filePath = Paths.get(TEST_FILE_PATH);
        FileCloner.cloneFile(filePath);

        Path copiedFilePath = Paths.get(TEST_FILE_PATH.replace(".txt", " — копия.txt"));
        assertTrue(Files.exists(copiedFilePath));

    }

    @Test
    void testCloneFileWith2Copies() {
        Path filePath = Paths.get(TEST_FILE_PATH);
        FileCloner.cloneFile(filePath);

        FileCloner.cloneFile(filePath);

        Path firstCopyPath = Paths.get(TEST_FILE_PATH.replace(".txt", " — копия.txt"));
        Path secondCopyPath = Paths.get(TEST_FILE_PATH.replace(".txt", " — копия (2).txt"));
        assertTrue(Files.exists(firstCopyPath));
        assertTrue(Files.exists(secondCopyPath));

    }

    private void createTestFile() {
        try {
            Path filePath = Paths.get(TEST_FILE_PATH);
            Files.createFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteTestFiles() {
        Path directoryPath = Paths.get("src/test/java/edu/hw6/task2/testFiles");

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {
            for (Path file : directoryStream) {
                Files.delete(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
