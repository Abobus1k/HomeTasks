package edu.hw1;

public class Task4 {

    private Task4() {

    }

    @SuppressWarnings("MagicNumber")
    public static String fixString(String brokenString) {
        if (brokenString.isEmpty()) {
            return brokenString;
        }

        StringBuilder sb = new StringBuilder();

        int len = brokenString.length();

        boolean hasEvenLength = true;

        if (len % 2 != 0) {
            len--;
            hasEvenLength = false;
        }

        int i = 0;

        while (i + 1 < len) {
            sb.append(brokenString.charAt(i + 1));
            sb.append(brokenString.charAt(i));
            i++;
            i++;
        }

        if (!hasEvenLength) {
            sb.append(brokenString.charAt(len));
        }

        return new String(sb);
    }
}
