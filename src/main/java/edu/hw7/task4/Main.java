package edu.hw7.task4;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        long startTime, finishTime;
        double seqPi, parPi;
        startTime = System.currentTimeMillis();
        seqPi = new MonteCarloPi(10000000).getPiInOneThread();
        finishTime = System.currentTimeMillis() - startTime;
        System.out.println(finishTime + "ms (sequential)");
        startTime = System.currentTimeMillis();
        parPi = new MonteCarloPi(10000000).getPiParallel(8);
        finishTime = System.currentTimeMillis() - startTime;
        System.out.println(finishTime + "ms (parallel)");
        System.out.println("Sequential Pi: " + seqPi);
        System.out.println("Parallel Pi: " + parPi);
    }
}

