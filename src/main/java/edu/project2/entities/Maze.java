package edu.project2.entities;

import java.util.Arrays;

public final class Maze {
    private final char[][] maze;
    private final int width;
    private final int height;
    public static final char WALL = '█';
    public static final char EMPTY = ' ';
    public static final char PATH = '*';

    public Maze(int width, int height) {
        maze = new char[width][height];
        this.width = width;
        this.height = height;
    }

    public Maze(char[][] givenMaze) {
        maze = new char[givenMaze.length][givenMaze[0].length];
        for (int i = 0; i < givenMaze.length; i++) {
            this.maze[i] = Arrays.copyOf(givenMaze[i], givenMaze[i].length);
        }
        this.height = givenMaze.length;
        this.width = givenMaze[0].length;
    }

    public void setMazeElement(Point point, char element) {
        maze[point.getX()][point.getY()] = element;
    }

    public char getMazeElement(Point point) {
        return maze[point.getX()][point.getY()];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
