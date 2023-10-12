package edu.hw1.task8;

public class Task8 {
    private Task8() {

    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1 && isCapture(board, new Point(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCapture(int[][] arr, Point point) {
        Point[] moves = initializeMoves();

        for (Point move : moves) {
            int x = point.x() + move.x();
            int y = point.y() + move.y();

            if (!outOfRange(arr, new Point(x, y)) && arr[x][y] == 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean outOfRange(int[][] arr, Point point) {
        return point.x() < 0 || point.x() > arr.length - 1 || point.y() < 0 || point.y() > arr[0].length - 1;
    }

    public static Point[] initializeMoves() {
        return new Point[] {
            new Point(2, 1),
            new Point(2, -1),
            new Point(1, 2),
            new Point(1, -2),
            new Point(-2, 1),
            new Point(-2, -1),
            new Point(-1, 2),
            new Point(-1, -2)
        };
    }
}


