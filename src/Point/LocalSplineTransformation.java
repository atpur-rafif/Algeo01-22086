package Point;

import java.util.function.Function;

import Matrix.Matrix;

public class LocalSplineTransformation extends Transformation{
    private static int pointCount = 4;
    private static int equationCount = GradientEquation.equationCount;
    private static int sideMatrixCount = pointCount * equationCount;

    public LocalSplineTransformation(Function<Point, GradientEquation> equation, int indepentdentVariableCount){
        this.matrix = new Matrix(sideMatrixCount, sideMatrixCount);
        for(int i = 0; i < pointCount; ++i){
            var EQ = equation.apply(points[i]).toArray();
            for(int j = 0; j < equationCount; ++j){
                for(int k = 0; k < indepentdentVariableCount; ++k){
                    matrix.set(j * pointCount + i, k, EQ[j].getCoefficient(k));
                }
            }
        }
    }

    private static Point[] points = {
        new Point(0, 0),
        new Point(1, 0),
        new Point(0, 1),
        new Point(1, 1)
    };
}
