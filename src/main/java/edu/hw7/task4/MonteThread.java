package edu.hw7.task4;

import java.util.Random;

public class MonteThread extends Thread {
    private final long dotsNumber;
    private long dotsInCircleNumber;

    public MonteThread(long dotsNumber) {
        this.dotsNumber = dotsNumber;
    }

    public long getDotsInCircleNumber() {
        return dotsInCircleNumber;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        double x;
        double y;
        for (int i = 0; i < dotsNumber; i++) {
            x = rnd.nextDouble();
            y = rnd.nextDouble();
            if (x * x + y * y < 1) {
                dotsInCircleNumber++;
            }
        }
    }
}
