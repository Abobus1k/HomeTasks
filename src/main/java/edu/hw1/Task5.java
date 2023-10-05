package edu.hw1;

public class Task5 {
    private Task5() {
    }

    public static boolean isPalindrom(int num) {
        String stringNum = String.valueOf(num);
        StringBuilder reversed = new StringBuilder(stringNum).reverse();
        return stringNum.equals(new String(reversed));
    }

    @SuppressWarnings("MagicNumber")
    public static boolean isPalindromDescendant(int num) {
        if (num < 10) {
            return false;
        }

        if (isPalindrom(num)) {
            return true;
        }

        String stringNum = String.valueOf(num);

        StringBuilder sb = new StringBuilder();

        int len = stringNum.length();

        boolean hasEvenLength = true;

        if (len % 2 != 0) {
            len--;
            hasEvenLength = false;
        }

        int i = 0;

        while (i + 1 < len) {
            int firstDigit = Character.getNumericValue(stringNum.charAt(i));
            int secondDigit = Character.getNumericValue(stringNum.charAt(i));
            String newDigit = String.valueOf(firstDigit + secondDigit);

            for (int j = 0; j < newDigit.length(); j++) {
                sb.append(newDigit.charAt(j));
            }
            i++;
            i++;
        }

        if (!hasEvenLength) {
            sb.append(stringNum.charAt(len));
        }

        // Глубина рекурсии будет достаточно мала, так как
        // количество вызовов ограничено количеством разрядов числа
        return isPalindromDescendant(Integer.parseInt(new String(sb)));
    }
}
