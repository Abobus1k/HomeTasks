package edu.hw1;

import java.util.Arrays;

public class Task3 {
    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1.length == 0 || a2.length == 0) {
            return true;
        }
        int a1_min = findMin(a1);
        int a1_max = findMax(a1);

        int a2_min = findMin(a2);
        int a2_max = findMax(a2);

        if (a1_min > a2_min && a1_max < a2_max) {
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
