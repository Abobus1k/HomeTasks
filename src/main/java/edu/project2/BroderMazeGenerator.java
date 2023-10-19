package edu.project2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import static edu.project2.Maze.EMPTY;
import static edu.project2.Maze.WALL;

public class BroderMazeGenerator implements MazeGenerator {
    private final int width;
    private final int height;
    private final Random random;

    public BroderMazeGenerator(int width, int height) {
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
        int x = random.nextInt(width / 2) * 2 + 1;
        int y = random.nextInt(height / 2) * 2 + 1;

        Point point = new Point(x, y);
        maze.setMazeElement(point, EMPTY);



        maze.setMazeElement(point, EMPTY);
        mazeBuilder(point, maze);

        return maze;
    }


    private void mazeBuilder(Point point, Maze maze) {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);

        for (Direction direction : directions) {
            Point newPoint = new Point(point.getX() + direction.dx, point.getY() + direction.dy);

            if (isInside(newPoint) && maze.getMazeElement(newPoint) == WALL) {
                Point pathDefaultPoint = new Point(point.getX() + direction.dx / 2, point.getY() + direction.dy / 2);

                maze.setMazeElement(pathDefaultPoint, EMPTY);
                maze.setMazeElement(newPoint, EMPTY);

                mazeBuilder(newPoint, maze);
            }
        }
    }

    private boolean isInside(Point point) {
        return point.getX() > 0 && point.getX() < width - 1 && point.getY() > 0 && point.getY() < height - 1;
    }
}

