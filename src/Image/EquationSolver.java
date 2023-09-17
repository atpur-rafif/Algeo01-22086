package Image;

import Matrix.*;
import Point.Point;

public class EquationSolver {
    private static int maxDegree = 3;
    private static int equationLength = 16;

    private static Point[] points = {
        new Point(0, 0),
        new Point(1, 0),
        new Point(0, 1),
        new Point(1, 1)
    };

    private static int sampleSideCount = 4;
    private static int coordinateOffset = (-1) * sampleSideCount + (-1);
    private static int flattenCoordinate(int x, int y){
        return (y * sampleSideCount) + x - coordinateOffset;
    }

    private static double[][] createEquation(Point p){
        int x = (int) p.x, y = (int) p.y;

        double[] f = new double[equationLength];
        f[flattenCoordinate(x, y)] = 1;

        double[] f_x = new double[equationLength];
        f_x[flattenCoordinate(x + 1, y)] = 0.5;
        f_x[flattenCoordinate(x - 1, y)] = -0.5;

        double[] f_y = new double[equationLength];
        f_y[flattenCoordinate(x, y + 1)] = 0.5;
        f_y[flattenCoordinate(x, y - 1)] = -0.5;

        double[] f_xy = new double[equationLength];
        f_xy[flattenCoordinate(x + 1, y + 1)] = 0.25;
        f_xy[flattenCoordinate(x - 1, y    )] = -0.25;
        f_xy[flattenCoordinate(x    , y - 1)] = -0.25;
        f_xy[flattenCoordinate(x    , y    )] = -0.25;

        double[][] r = {f, f_x, f_y, f_xy};
        return r;
    }

    private static Matrix createMatrix() {
        var M = new Matrix(equationLength, equationLength);
        var Manipulator = new MatrixManipulator(M);

        int i, j;
        for (i = 0; i <= maxDegree; ++i) {
            var e = createEquation(points[i]);
            for (j = 0; j <= maxDegree; ++j) {
                Manipulator.setRow(j * 4 + i, e[j]);
            }
        }

        M = Manipulator.getResult();

        return M;
    }

    public static Matrix MatrixD = createMatrix();
}
