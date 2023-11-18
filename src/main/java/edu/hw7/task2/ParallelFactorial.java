package edu.hw7.task2;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class ParallelFactorial {
    public static BigInteger factorial(int n) {
        return LongStream.rangeClosed(1, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public static BigInteger factorial2(int n) {
        return LongStream.rangeClosed(1, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }

    public static void main(String[] args) {
        int number = 2000000;
        long start1 = System.currentTimeMillis();
        factorial(number);
        //System.out.println(factorial(number));
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
        long start2 = System.currentTimeMillis();
        factorial2(number);
        //System.out.println(factorial2(number));
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);
    }
}
