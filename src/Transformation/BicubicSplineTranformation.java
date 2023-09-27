package Transformation;

import Matrix.MatrixInverse;
import Vector.BicubicSplineSpace;

public class BicubicSplineTranformation extends LocalSplineTransformation{
    public BicubicSplineTranformation(){
        super(BicubicSplineSpace.createGradientEquationLambda);
        this.matrix = MatrixInverse.calculateWithGaussJordan(this.matrix);
    }
}
