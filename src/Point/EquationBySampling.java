package Point;

import java.util.function.Function;
import Matrix.*;

public class EquationBySampling {
    public static int maxDegree = 3;
    public static int equationLength = 16;

    private static Point[] points = {
        new Point(0, 0),
        new Point(1, 0),
        new Point(0, 1),
        new Point(1, 1)
    };

    public static Matrix createMatrix(Function<Point, double[][]> equationMaker){
        var M = new Matrix(equationLength, equationLength);
        var Manipulator = new MatrixManipulator(M);

        int i, j;
        for(i = 0; i <= maxDegree; ++i){
            double[][] e = equationMaker.apply(points[i]);
            for(j = 0; j <= maxDegree; ++j){
                Manipulator.setRow(j * 4 + i, e[j]);
            }
        }

        M = Manipulator.getResult();

        return M;
    }
}
