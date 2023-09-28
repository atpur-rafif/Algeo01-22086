package Transformation;

import java.util.function.Function;
import Matrix.*;
import Vector.*;
import Point.Point;

public class LocalSplineTransformation extends Transformation{
    public static Point[] points = {
        new Point(0, 0),
        new Point(1, 0),
        new Point(0, 1),
        new Point(1, 1)
    };
    
    public static int pointCount = points.length;
    public static int equationCount = GradientEquation.equationCount;
    private static int sideMatrixCount = pointCount * equationCount;

    public LocalSplineTransformation(Function<Point, GradientEquation> equation){
        this.matrix = new Matrix(sideMatrixCount, sideMatrixCount);
        for(int i = 0; i < pointCount; ++i){
            var EQ = equation.apply(points[i]).toArray();
            for(int j = 0; j < equationCount; ++j){
                for(int k = 0; k < EQ[j].independentVariableCount; ++k){
                    matrix.set(j * pointCount + i, k, EQ[j].get(k));
                }
            }
        }
    }
}
