package edu.project2.renders;

import edu.project2.entities.Maze;
import edu.project2.entities.Point;
import java.util.List;
import static edu.project2.entities.Maze.PATH;

public final class MazeRender {

    private MazeRender() {
    }

    public static String viewMaze(Maze maze) {
        StringBuilder currentMaze = new StringBuilder();
        for (int x = 0; x < maze.getHeight(); x++) {
            for (int y = 0; y < maze.getWidth(); y++) {
                currentMaze.append(maze.getMazeElement(new Point(x, y)));
            }
            currentMaze.append('\n');
        }
        return currentMaze.toString();
    }

    public static String viewMazeWithPath(Maze maze, List<Point> path) {
        for (Point point : path) {
            maze.setMazeElement(point, PATH);
        }
        return viewMaze(maze);
    }
}
