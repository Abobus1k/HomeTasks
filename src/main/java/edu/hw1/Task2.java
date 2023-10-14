package edu.hw1;

public class Task2 {

    private Task2() {

    }

    @SuppressWarnings("MagicNumber")
    public static int countOfDigits(int num) {
        int tmp = Math.abs(num);
        int res = 0;

        do {
            tmp /= 10;
            res++;
        } while (tmp != 0);

        return res;
    }
}
