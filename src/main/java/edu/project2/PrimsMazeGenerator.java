package edu.project2;

import java.util.ArrayList;
import java.util.Random;
import static edu.project2.Maze.EMPTY;
import static edu.project2.Maze.WALL;

public class PrimsMazeGenerator implements MazeGenerator {
    private final int width;
    private final int height;
    private final Random random;

    public PrimsMazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        random = new Random();
    }

    public Maze generateMaze() {
        Maze maze = new Maze(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                maze.setMazeElement(new Point(x, y), WALL);
            }
        }
        int startX = random.nextInt(width / 2) * 2 + 1;
        int startY = random.nextInt(height / 2) * 2 + 1;
        maze.setMazeElement(new Point(startX, startY), EMPTY);

        ArrayList<Point> frontier = new ArrayList<>();
        frontier.add(new Point(startX, startY));

        while (!frontier.isEmpty()) {
            Point currentPoint = frontier.remove(random.nextInt(frontier.size()));

            Direction[] directions = Direction.values();

            for (Direction direction : directions) {
                Point newPoint = new Point(currentPoint.getX() + direction.dx,
                    currentPoint.getY() + direction.dy
                );

                if (isInside(newPoint) && maze.getMazeElement(newPoint) == WALL) {
                    maze.setMazeElement(newPoint, EMPTY);
                    maze.setMazeElement(new Point(
                        currentPoint.getX() + direction.dx / 2,
                        currentPoint.getY() + direction.dy / 2
                    ), EMPTY);
                    frontier.add(newPoint);
                }
            }

        }
        return maze;
    }

    private boolean isInside(Point point) {
        return point.getX() > 0 && point.getX() < width && point.getY() > 0 && point.getY() < height;
    }
}
