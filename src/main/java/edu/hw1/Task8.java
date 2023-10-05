package edu.hw1;

public class Task8 {
    private Task8() {

    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1 && isCapture(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("MagicValue")
    public static boolean isCapture(int[][] arr, int i, int j) {
        int[] xAxis = {2, 2, 1, 1, -2, -2, -1, -1};
        int[] yAxis = {1, -1, 2, -2, 1, -1, -2, 2};

        for (int k = 0; k < 8; k++) {
            int x = i + xAxis[k];
            int y = j + yAxis[k];

            if (!outOfRange(arr, x, y) && arr[x][y] == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean outOfRange(int[][] arr, int i, int j) {
        return i < 0 || i > arr.length - 1 || j < 0 || j > arr[0].length - 1;
    }
}
