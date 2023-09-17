package Image;

import Point.Point;

public class EquationSolver {
    static Point[] points = {
        new Point(0, 0),
        new Point(0, 1),
        new Point(1, 0),
        new Point(1, 1)
    };

    static int sampleSideCount = 4;
    static int coordinateOffset = (-1) * sampleSideCount + (-1);
    public static int flattenCoordinate(int x, int y){
        return (y * sampleSideCount) + x - coordinateOffset;
    }

}
