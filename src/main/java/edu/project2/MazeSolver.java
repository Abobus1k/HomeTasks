package edu.project2;

import java.util.List;

public interface MazeSolver {

    boolean findPath(Point startPoint, Point endPoint);

    List<Point> getPath();
}
