package edu.hw1;

public class Task2 {
    private Task2() {

    }
    @SuppressWarnings("MagicNumber")
    public static int countOfDigits(int num) {
        if (num == 0) {
            return 1;
        }
        int tmp = num;

        int res = 0;
        while (tmp != 0) {
            tmp /= 10;
            res++;
        }
        return res;
    }
}
