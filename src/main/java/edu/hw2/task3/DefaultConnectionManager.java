package edu.hw2.task3;

import edu.hw2.task2.Rectangle;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final Random random = new Random();

    @Override
    public Connection getConnection() {
        return random.nextBoolean() ? new StableConnection() : new FaultyConnection();
    }
}
