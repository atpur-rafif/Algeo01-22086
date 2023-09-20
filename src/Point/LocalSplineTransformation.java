package Point;

import java.util.function.Function;

public class LocalSplineTransformation extends Transformation{
    private static int maxDegree = 3;
    private static int pointCount = 4;
    private static int equationCount = 4; // f f_x f_y f_xy
    private static int equationLength = 16;

    LocalSplineTransformation(Function<Point, GradientEquation> equation){

    }

    private static Point[] points = {
        new Point(0, 0),
        new Point(1, 0),
        new Point(0, 1),
        new Point(1, 1)
    };
}
