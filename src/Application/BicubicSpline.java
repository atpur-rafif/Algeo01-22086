package Application;

import Point.Point;
import Vector.*;
import Transformation.*;

public class BicubicSpline{
    private static int indepentdentVariableCount = BicubicSplineSpace.indepentdentVariableCount;
    private static Transformation transformation = new BicubicSplineTranformation();

    public static EquationSpace getEquation(VectorSpace v){
        var ev = transformation.apply(v);
        var e = new EquationSpace(indepentdentVariableCount);
        for(int i = 0; i < indepentdentVariableCount; ++i) e.set(i, ev.get(i));
        return e;
    }

    public static double approximate(EquationSpace eq, double x, double y){
        var v = BicubicSplineSpace.createF(new Point(x, y));
        double r = 0;
        for(int i = 0; i < BicubicSplineSpace.indepentdentVariableCount; ++i){
            r += eq.get(i) * v.get(i);
        }
        return r;
    }
}
