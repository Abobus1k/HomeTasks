package edu.hw10.task2;

public class FibCalculatorImpl implements FibCalculator {
    @Cache(persist = true)
    @Override
    public long fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            long fibPrev = 0;
            long fibCurrent = 1;

            for (int i = 2; i <= n; i++) {
                long temp = fibCurrent;
                fibCurrent = fibPrev + fibCurrent;
                fibPrev = temp;
            }

            return fibCurrent;
        }
    }
}
