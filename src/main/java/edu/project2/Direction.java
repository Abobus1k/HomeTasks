package edu.project2;

public enum Direction {
    UP(0, -2),
    DOWN(0, 2),
    LEFT(-2, 0),
    RIGHT(2, 0);

    final int dx;
    final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
