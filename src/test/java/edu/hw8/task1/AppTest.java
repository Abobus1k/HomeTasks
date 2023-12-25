package edu.hw8.task1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {

    private Server server;
    private PipedOutputStream serverOutput;
    private ByteArrayOutputStream clientOutput;
    private PrintStream originalSystemOut;
    private InputStream originalSystemIn;
    private static final String path = "src/test/java/edu/hw8/task1/testFiles/server_log.txt";

    @BeforeEach
    void setUp() {
        serverOutput = new PipedOutputStream();
        clientOutput = new ByteArrayOutputStream();
        originalSystemOut = System.out;
        originalSystemIn = System.in;

        System.setOut(new PrintStream(clientOutput));

        server = new Server(path);
        server.start();
    }

    @AfterEach
    void shutDown() throws IOException, InterruptedException {
        TimeUnit.MILLISECONDS.sleep(1000);
        if (server.isAlive()) {
            server.shutdown();
        }

        serverOutput.close();
        clientOutput.close();

        System.setOut(originalSystemOut);
        System.setIn(originalSystemIn);
    }

    @Test
    void testClientServer() throws IOException, InterruptedException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream("личности\nexit\n".getBytes())) {
            System.setIn(inputStream);

            Client client = new Client();
            client.start();

            String expectedLogEntry = "IP: 127.0.0.1, Request: личности, Response: Не переходи на личности там, где их нет";
            assertTrue(waitForLogEntry(expectedLogEntry));
        }
    }

    @Test
    void testMultipleClientServer() throws IOException, InterruptedException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream("личности\nexit\n".getBytes())) {
            System.setIn(inputStream);

            Client client = new Client();
            client.start();

            String expectedLogEntry1 = "IP: 127.0.0.1, Request: личности, Response: Не переходи на личности там, где их нет";
            assertTrue(waitForLogEntry(expectedLogEntry1));
        }

        try (ByteArrayInputStream inputStream = new ByteArrayInputStream("оскорбления\nexit\n".getBytes())) {
            System.setIn(inputStream);

            Client client = new Client();
            client.start();

            String expectedLogEntry2 = "IP: 127.0.0.1, Request: оскорбления, Response: Если твои противники перешли на личные оскорбления, будь уверен — твоя победа не за горами";
            assertTrue(waitForLogEntry(expectedLogEntry2));
        }
    }

    private boolean waitForLogEntry(String expectedLogEntry) throws IOException, InterruptedException {
        int timeout = 5000;
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < timeout) {
            if (logContains(expectedLogEntry)) {
                return true;
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }

        return false;
    }

    private boolean logContains(String expectedLogEntry) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(expectedLogEntry)) {
                    return true;
                }
            }
        }
        return false;
    }

    @AfterAll
    static void cleanUp() {
        try {
            clearLogFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearLogFile(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
        }
    }
}
