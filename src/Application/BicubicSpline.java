package Application;

import Point.*;
import Matrix.MatrixInverse;

public class BicubicSpline{
    private static int indepentdentVariableCount = BicubicSplineEquation.indepentdentVariableCount;
    private static Transformation transformation = createTransformation();
    private static Transformation createTransformation(){
        var t = new LocalSplineTransformation(BicubicSplineEquation.createEquationLambda);
        t.matrix = MatrixInverse.calculateWithGaussJordan(t.matrix);
        return t;
    }

    public static Equation getEquation(Vector v){
        var ev = transformation.apply(v);
        var e = new Equation(indepentdentVariableCount);
        for(int i = 0; i < indepentdentVariableCount; ++i) e.setCoefficient(i, ev.getComponent(i));
        return e;
    }

    public static double approximate(Equation eq, double x, double y){
        var v = BicubicSplineEquation.createF(new Point(x, y));
        double r = 0;
        for(int i = 0; i < BicubicSplineEquation.indepentdentVariableCount; ++i){
            r += eq.getCoefficient(i) * v.getCoefficient(i);
        }
        return r;
    }
}
