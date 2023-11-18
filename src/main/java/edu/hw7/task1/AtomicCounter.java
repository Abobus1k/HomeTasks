package edu.hw7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private final AtomicInteger value;

    public AtomicCounter(int value) {
        this.value = new AtomicInteger(value);
    }

    public int increment() {
        return value.incrementAndGet();
    }

    public int getValue() {
        return value.get();
    }
}
