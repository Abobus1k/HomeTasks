package edu.hw7.task4;

import java.util.Random;

public class MonteCarloPi {
    private final long dotsNumber;
    private static final double SQUARE_SIDE = 4.0;

    public MonteCarloPi(long dotsNumber) {
        this.dotsNumber = dotsNumber;
    }

    public double getPiInOneThread() {
        double x;
        double y;
        long passed = 0;
        Random rnd = new Random();

        for (int i = 0; i < dotsNumber; i++) {
            x = rnd.nextDouble();
            y = rnd.nextDouble();
            if (x * x + y * y < 1) {
                passed++;
            }
        }
        return ((double) passed / dotsNumber) * SQUARE_SIDE;
    }

    public double getPiParallel(int threadsNumber) throws InterruptedException {
        long dotsInCircleNumber = 0;
        long dotsInThreadNumber = dotsNumber / threadsNumber;
        MonteThread[] customThreads = new MonteThread[threadsNumber];

        for (int i = 0; i < threadsNumber; i++) {
            customThreads[i] = new MonteThread(dotsInThreadNumber);
            customThreads[i].start();
        }

        for (MonteThread customThread : customThreads) {
            customThread.join();
            dotsInCircleNumber += customThread.getDotsInCircleNumber();
        }

        return ((double) dotsInCircleNumber / dotsNumber) * SQUARE_SIDE;
    }
}
