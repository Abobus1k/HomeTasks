package edu.hw8.task2;

import java.util.concurrent.CountDownLatch;

public class Fibonacci {

    private final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    public long[] parallelFib(int threadsNumber) throws Exception {
        long[] ans = new long[n];

        ThreadPool threadPool = FixedThreadPool.create(threadsNumber);
        threadPool.start();

        CountDownLatch latch = new CountDownLatch(n);

        for (int i = 0; i < n; i++) {
            int tmp = i;
            threadPool.execute(() -> {
                ans[tmp] = fibonacci(tmp);
                latch.countDown();
            });
        }

        latch.await();

        threadPool.close();

        return ans;
    }

    private static long fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
