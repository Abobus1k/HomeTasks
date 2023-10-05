package edu.hw1;

import java.util.Arrays;

public class Task3 {
    private Task3() {

    }
    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1.length == 0 || a2.length == 0) {
            return true;
        }
        int firstMin = findMin(a1);
        int firstMax = findMax(a1);

        int secondMin = findMin(a2);
        int secondMax = findMax(a2);

        if (firstMin > secondMin && firstMax < secondMax) {
            return true;
        }
        return false;
    }

    public static int findMin(int[] arr) {
        return Arrays.stream(arr).min().getAsInt();
    }

    public static int findMax(int[] arr) {
        return Arrays.stream(arr).max().getAsInt();
    }
}
