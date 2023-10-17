package edu.hw3.task4;

import java.util.HashMap;
import java.util.Map;

public final class Task4Util {

    private Task4Util() {

    }

    public static String romanToInteger(int romanNumber) {

        Map<Integer, String> romanMap = filledRomanMap();

        int[] convertArray = filledConvertArray();

        StringBuilder result = new StringBuilder();

        for (int integer: convertArray) {
            while (romanNumber >= integer) {
                result.append(romanMap.get(integer));
                romanNumber -= integer;
            }
        }
        return result.toString();
    }

    private static Map<Integer, String> filledRomanMap() {
        Map<Integer, String> romanMap = new HashMap<>();

        romanMap.put(1, "I");
        romanMap.put(4, "IV");
        romanMap.put(5, "V");
        romanMap.put(9, "IX");
        romanMap.put(10, "X");
        romanMap.put(40, "XL");
        romanMap.put(50, "L");
        romanMap.put(90, "XC");
        romanMap.put(100, "C");
        romanMap.put(400, "CD");
        romanMap.put(500, "D");
        romanMap.put(900, "CM");
        romanMap.put(1000, "M");

        return romanMap;
    }

    private static int[] filledConvertArray() {
        return new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5,4, 1};
    }
}
