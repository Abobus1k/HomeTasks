package edu.hw2.task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RemoteServerTest {
    @Test
    void testFaultyConnectionManager() {
        ConnectionManager manager = new FaultyConnectionManager();
        int maxAttempts = 1;
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        boolean exceptionThrown = false;

        for (int i = 0; i < 50; i++) {
            try {
                executor.updatePackages();
            } catch (ConnectionException ex) {
                exceptionThrown = true;
                break;
            }
        }

        Assertions.assertTrue(exceptionThrown);
    }

    @Test
    void testDefaultConnectionManager() {
        ConnectionManager manager = new DefaultConnectionManager();
        int maxAttempts = 5;
        PopularCommandExecutor executor = new PopularCommandExecutor(manager, maxAttempts);

        boolean exceptionThrown = false;

        for (int i = 0; i < 50; i++) {
            try {
                executor.updatePackages();
            } catch (ConnectionException ex) {
                exceptionThrown = true;
                break;
            }
        }

        Assertions.assertFalse(exceptionThrown);
    }
}
