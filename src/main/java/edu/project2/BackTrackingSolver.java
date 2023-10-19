package edu.project2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BackTrackingSolver implements MazeSolver {
    private final int rows;
    private final int cols;
    private final Set<Point> visited;
    private final List<Point> path;
    private final Maze maze;

    public BackTrackingSolver(Maze maze) {
        this.maze = maze;
        this.rows = maze.getHeight();
        this.cols = maze.getWidth();
        this.visited = new HashSet<>();
        this.path = new ArrayList<>();
    }

    public boolean findPath(Point startPoint, Point endPoint) {
        if (startPoint.getX() < 0 || startPoint.getX() >= rows
            || startPoint.getY() < 0 || startPoint.getY() >= cols
            || maze.getMazeElement(startPoint) == Maze.WALL || visited.contains(startPoint)) {
            return false;
        }

        visited.add(startPoint);
        path.add(startPoint);

        if (startPoint.getX() == endPoint.getX() && startPoint.getY() == endPoint.getY()) {
            return true;
        }

        if (findPath(new Point(startPoint.getX() + 1, startPoint.getY()), endPoint)
            || findPath(new Point(startPoint.getX() - 1, startPoint.getY()), endPoint)
            || findPath(new Point(startPoint.getX(), startPoint.getY() + 1), endPoint)
            || findPath(new Point(startPoint.getX(), startPoint.getY() - 1), endPoint)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public List<Point> getPath() {
        return path;
    }
}
