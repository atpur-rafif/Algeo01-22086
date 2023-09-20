package Application;

import Point.BicubicSplineEquation;
import Point.LocalSplineTransformation;

public class BicubicSpline extends LocalSplineTransformation{
    public BicubicSpline(){
        super(
            BicubicSplineEquation.createEquationLambda, 
            BicubicSplineEquation.indepentdentVariableCount
        );
    }

}
