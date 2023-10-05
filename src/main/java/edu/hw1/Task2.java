package edu.hw1;

public class Task2 {
    public static int countOfDigits(int num) {
        if (num == 0) return 1;

        int res = 0;
        while (num != 0) {
            num /= 10;
            res++;
        }
        return res;
    }
}
