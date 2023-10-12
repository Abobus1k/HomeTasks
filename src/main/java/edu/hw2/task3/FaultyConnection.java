package edu.hw2.task3;

import java.util.Random;

public class FaultyConnection implements Connection {
    private final Random random = new Random();

    @Override
    public void execute(String command) {
        if (random.nextBoolean()) {
            throw new ConnectionException("Connection failed.");
        }
    }

    @Override
    public void close() throws Exception {

    }
}
