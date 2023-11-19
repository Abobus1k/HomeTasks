package edu.hw8.task3;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class PasswordCrackerTest {

    @Test
    void testSingleThreadedPasswordCrack() {
        Map<String, String> passwordDatabase = createSamplePasswordDatabase();
        PasswordCracker passwordCracker = new PasswordCracker(passwordDatabase);

        passwordCracker.singleThreadedPasswordCrack();

        ConcurrentHashMap<String, String> cracked = passwordCracker.getCracked();

        assertTrue(cracked.containsKey("Name2"));
        assertTrue(cracked.containsKey("Name3"));
        assertTrue(cracked.containsKey("Name4"));

        assertEquals("abc", cracked.get("Name2"));
        assertEquals("svet", cracked.get("Name4"));
        assertEquals("no", cracked.get("Name3"));
    }

    @Test
    void testMultiThreadedPasswordCrack() throws InterruptedException {
        Map<String, String> passwordDatabase = createSamplePasswordDatabase();
        PasswordCracker passwordCracker = new PasswordCracker(passwordDatabase);

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        passwordCracker.multiThreadedPasswordCrack(executorService, 4);
        executorService.shutdown();

        ConcurrentHashMap<String, String> cracked = passwordCracker.getCracked();


        assertTrue(cracked.containsKey("Name2"));
        assertTrue(cracked.containsKey("Name3"));
        assertTrue(cracked.containsKey("Name4"));

        assertEquals("abc", cracked.get("Name2"));
        assertEquals("svet", cracked.get("Name4"));
        assertEquals("no", cracked.get("Name3"));
    }

    private Map<String, String> createSamplePasswordDatabase() {
        Map<String, String> passwordDatabase = new HashMap<>();
        passwordDatabase.put("45c48cce2e2d7fbdea1afc51c7c6ad26", "Name1");
        passwordDatabase.put("900150983cd24fb0d6963f7d28e17f72", "Name2");
        passwordDatabase.put("7fa3b767c460b54a2be4d49030b349c7", "Name3");
        passwordDatabase.put("e241e04edb6f654281bfdc8989a9d4b2", "Name4");
        return passwordDatabase;
    }
}
