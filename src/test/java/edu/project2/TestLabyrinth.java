package edu.project2;

import edu.project2.entities.Maze;
import edu.project2.entities.Point;
import edu.project2.entities.SmartPoint;
import edu.project2.solvers.BFSSolver;
import edu.project2.solvers.BackTrackingSolver;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static edu.project2.renders.MazeRender.viewMaze;
import static edu.project2.renders.MazeRender.viewMazeWithPath;

public class TestLabyrinth {
    private static char[][] mazeWithoutPath;
    private static String outputMazeWithoutPath;
    private static String outputMazeWithPath;


    @BeforeAll
    static void setUp() {
        TestLabyrinth.mazeWithoutPath = new char[][] {
            {'█', '█', '█', '█', '█', '█', '█'},
            {'█', ' ', '█', ' ', ' ', ' ', '█'},
            {'█', ' ', '█', '█', '█', ' ', '█'},
            {'█', ' ', ' ', ' ', '█', ' ', '█'},
            {'█', ' ', '█', ' ', '█', ' ', '█'},
            {'█', ' ', '█', ' ', ' ', ' ', '█'},
            {'█', '█', '█', '█', '█', '█', '█'}
        };

        outputMazeWithPath = "███████\n█*█   █\n█*███ █\n█***█ █\n█ █*█ █\n█ █***█\n███████\n";
        outputMazeWithoutPath = "███████\n█ █   █\n█ ███ █\n█   █ █\n█ █ █ █\n█ █   █\n███████\n";
    }

    @Test
    void testMazeWithoutPathRender() {
        String maze = viewMaze(new Maze(mazeWithoutPath));

        Assertions.assertEquals(outputMazeWithoutPath, maze);
    }

    @Test
    void testFindPathByBFS() {
        BFSSolver solver = new BFSSolver(new Maze(mazeWithoutPath));

        Assertions.assertTrue(solver.findPath(new Point(1, 1), new Point(5, 5)));
    }

    @Test
    void testFindPathByBackTracking() {
        BackTrackingSolver solver = new BackTrackingSolver(new Maze(mazeWithoutPath));

        Assertions.assertTrue(solver.findPath(new Point(1, 1), new Point(5, 5)));
    }

    @Test
    void testGetPathByBFS() {
        SmartPoint point1 = new SmartPoint(1,1, null);
        List<SmartPoint> rightPath = getSmartPoints(point1);

        BFSSolver solver = new BFSSolver(new Maze(mazeWithoutPath));
        solver.findPath(new Point(1, 1), new Point(5, 5));
        List<Point> path = solver.getPath();
        path = path.reversed();

        Assertions.assertEquals(rightPath, path);
    }

    @NotNull private static List<SmartPoint> getSmartPoints(SmartPoint point1) {
        SmartPoint point2 = new SmartPoint(2,1,point1);
        SmartPoint point3 = new SmartPoint(3,1, point2);
        SmartPoint point4 = new SmartPoint(3,2,point3);
        SmartPoint point5 = new SmartPoint(3,3,point4);
        SmartPoint point6 = new SmartPoint(4,3,point5);
        SmartPoint point7 = new SmartPoint(5,3,point6);
        SmartPoint point8 = new SmartPoint(5,4,point7);
        SmartPoint point9 = new SmartPoint(5,5, point8);

        return List.of(
            point1, point2, point3, point4, point5,
            point6, point7, point8, point9
        );
    }

    @Test
    void testGetPathByBackTracking() {
        List<Point> rightPath = List.of(
            new Point(1,1), new Point(2,1), new Point(3,1),
            new Point(3,2), new Point(3,3), new Point(4,3),
            new Point(5,3), new Point(5,4), new Point(5,5)
        );

        BackTrackingSolver solver = new BackTrackingSolver(new Maze(mazeWithoutPath));
        solver.findPath(new Point(1, 1), new Point(5, 5));
        List<Point> path = solver.getPath();

        Assertions.assertEquals(rightPath, path);
    }

    @Test
    void testMazeWithPathRender() {
        Maze currentMaze = new Maze(mazeWithoutPath);
        BFSSolver solver = new BFSSolver(currentMaze);

        solver.findPath(new Point(1, 1), new Point(5, 5));

        List<Point> path = solver.getPath();

        String maze = viewMazeWithPath(currentMaze, path);

        Assertions.assertEquals(outputMazeWithPath, maze);
    }
}
