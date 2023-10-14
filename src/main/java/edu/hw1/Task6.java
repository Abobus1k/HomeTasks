package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private Task6() {
    }

    @SuppressWarnings("MagicNumber")
    public static int countK(int num) {
        return kaprekar(num, 0);
    }

    @SuppressWarnings("MagicNumber")
    public static int kaprekar(int num, int count) {
        if (num == 6174) {
            return count;
        }
        String number = String.valueOf(num);

        char[] ascendingChars = number.toCharArray();
        char[] descendingChars = number.toCharArray();

        Arrays.sort(descendingChars);

        for (int i = 0; i < ascendingChars.length; i++) {
            ascendingChars[i] = descendingChars[ascendingChars.length - i - 1];
        }

        String ascendingNum = new String(ascendingChars);
        String descendingNum = new String(descendingChars);

        int firstNum = Integer.parseInt(ascendingNum);
        int secondNum = Integer.parseInt(descendingNum);

        int res = firstNum - secondNum;

        if (res < 1000) {
            res *= 10;
        }

        return kaprekar(res, count + 1);
    }
}
