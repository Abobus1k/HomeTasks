package edu.project2.solvers;

import edu.project2.entities.Point;

import java.util.List;

public interface MazeSolver {

    boolean findPath(Point startPoint, Point endPoint);

    List<Point> getPath();
}
