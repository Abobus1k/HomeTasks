package edu.hw7.task1;

public class ParallelIncrementer {

    private final int threadNumber;
    private final AtomicCounter counter;

    public ParallelIncrementer(int threadNumber, int counter) {
        this.threadNumber = threadNumber;
        this.counter = new AtomicCounter(counter);
    }

    public int execute() {
        Thread[] threads = new Thread[threadNumber];

        for (int i = 0; i < threadNumber; i++) {
            threads[i] = new Thread(new CounterTask(counter));
            threads[i].start();
        }

        try {
            for (int i = 0; i < threadNumber; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return counter.getValue();
    }

    public static void main(String[] args) {
        ParallelIncrementer parallelIncrementer = new ParallelIncrementer(10, 10);
        System.out.println(parallelIncrementer.execute());
    }
}
