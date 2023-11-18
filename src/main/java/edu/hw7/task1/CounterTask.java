package edu.hw7.task1;

public class CounterTask implements Runnable {
    private AtomicCounter counter;

    public CounterTask(AtomicCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.increment();
    }
}
