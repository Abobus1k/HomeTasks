package edu.project2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSolver implements MazeSolver {

    private final int rows;
    private final int cols;
    private final Queue<SmartPoint> queue;
    private final boolean[][] visited;
    private final Maze maze;
    private SmartPoint finishPoint;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    public BFSSolver(Maze maze) {
        this.maze = maze;
        this.rows = maze.getHeight();
        this.cols = maze.getWidth();
        this.visited = new boolean[rows][cols];
        this.queue = new LinkedList<>();
    }

    @Override
    public boolean findPath(Point startPoint, Point endPoint) {
        return endOfMaze(new SmartPoint(startPoint, null), new SmartPoint(endPoint, null));
    }

    public boolean endOfMaze(SmartPoint startPoint, SmartPoint endPoint) {
        queue.add(startPoint);
        visited[startPoint.getX()][startPoint.getY()] = true;

        while (!queue.isEmpty()) {
            SmartPoint currentPoint = queue.poll();

            if (currentPoint.getX() == endPoint.getX() && currentPoint.getY() == endPoint.getY()) {
                finishPoint = currentPoint;
                return true;
            }

            for (int i = 0; i < DX.length; i++) {
                int newX = currentPoint.getX() + DX[i];
                int newY = currentPoint.getY() + DY[i];

                if (isValid(new Point(newX, newY)) && !visited[newX][newY]) {
                    queue.add(new SmartPoint(newX, newY, currentPoint));
                    visited[newX][newY] = true;
                }
            }
        }
        return false;
    }

    public boolean isValid(Point point) {
        return point.getX() >= 0 && point.getX() < rows
            && point.getY() >= 0 && point.getY() < cols
            && maze.getMazeElement(point) == Maze.EMPTY;
    }

    @Override
    public List<Point> getPath() {
        List<Point> result = new ArrayList<>();
        SmartPoint current = finishPoint;
        while (current != null) {
            result.add((Point) current);
            current = (current.getParent());
        }
        return result;
    }
}
