package edu.hw8.task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int threadsNumber;
    private final BlockingQueue<Runnable> taskQueue;
    private final Thread[] threads;
    private volatile boolean isShutdown;

    public FixedThreadPool(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new Thread[threadsNumber];
        this.isShutdown = false;
    }

    @Override
    public void start() {
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new Thread(new Worker());
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isShutdown) {
            throw new IllegalStateException();
        }
        try {
            taskQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        isShutdown = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private class Worker implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static FixedThreadPool create(int threadsNumber) {
        return new FixedThreadPool(threadsNumber);
    }
}
