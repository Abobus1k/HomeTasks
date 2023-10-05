package edu.hw1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Task7 {
    private Task7() {
    }

    public static Deque<Integer> createBinaryQueue(int num) {
        String binaryStr = Integer.toBinaryString(num);

        Deque<Integer> binaryQueue = new ArrayDeque<>();

        for (int i = 0; i < binaryStr.length(); i++) {
            int digit = Character.getNumericValue(binaryStr.charAt(i));
            binaryQueue.addLast(digit);
        }

        return binaryQueue;
    }

    public static int rotateLeft(int n, int shift) {
        Deque<Integer> binaryQueue = createBinaryQueue(n);

        for (int i = 0; i < shift; i++) {
            int currentDigit = binaryQueue.removeFirst();
            binaryQueue.addLast(currentDigit);
        }

        return getResultNumber(binaryQueue);
    }

    public static int rotateRight(int n, int shift) {
        Deque<Integer> binaryQueue = createBinaryQueue(n);

        for (int i = 0; i < shift; i++) {
            int currentDigit = binaryQueue.removeLast();
            binaryQueue.addFirst(currentDigit);
        }

        return getResultNumber(binaryQueue);
    }

    public static int getResultNumber(Deque<Integer> deque) {
        int number = 0;

        while (!deque.isEmpty()) {
            int digit = deque.removeFirst();
            number = (number << 1) | digit;
        }

        return number;
    }
}
