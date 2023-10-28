package edu.project2.generators;

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
