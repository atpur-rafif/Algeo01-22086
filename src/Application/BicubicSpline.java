package Application;

import Matrix.*;
import Point.*;

public class BicubicSpline {
    private static int maxDegree = EquationBySampling_Deprecated.maxDegree;
    private static int equationLength = EquationBySampling_Deprecated.equationLength;
    private static int pointCount = EquationBySampling_Deprecated.pointCount;
    private static int equationCount = EquationBySampling_Deprecated.equationCount;

    private static double pow(double x, int y){
        double r = 1;
        for(; y > 0; --y) r *= x;
        return r;
    }

    public static Matrix MatrixX = EquationBySampling_Deprecated.createMatrix((var p) -> {
        double x = p.x, y = p.y;
        double[] f = new double[equationLength];
        double[] f_x = new double[equationLength];
        double[] f_y = new double[equationLength];
        double[] f_xy = new double[equationLength];

        int i, j, k = 0;
        for(j = 0; j <= maxDegree; ++j){
            for(i = 0; i <= maxDegree; ++i){
                f[k] = pow(x, i) * pow(y, j);
                f_x[k] = i * pow(x, i - 1) * pow(y, j);
                f_y[k] = j * pow(x, i) * pow(y, j - 1);
                f_xy[k] = i * j * pow(x, i - 1) * pow(y, j - 1);
                ++k;
            }
        }
        
        double[][] r = {f, f_x, f_y, f_xy};
        return r;
    });

    public static Matrix MatrixIX = MatrixInverse.calculateWithGaussJordan(MatrixX);

    /*
        F is vector matrix with
        f   (0, 0) 
        f   (1, 0) 
        f   (0, 1) 
        f   (1, 1)
        f_x (0, 0) 
        f_x (1, 0) 
        f_x (0, 1) 
        f_x (1, 1)
        f_y (0, 0) 
        f_y (1, 0) 
        f_y (0, 1) 
        f_y (1, 1)
        f_xy(0, 0) 
        f_xy(1, 0) 
        f_xy(0, 1) 
        f_xy(1, 1)
    */
    public static Equation getEquation(Matrix F){
        var e = new Equation(equationLength);

        var v = MatrixArithmetic.Multiply(MatrixIX, F);
        for(int i = 0; i < equationLength; ++i) e.setCoefficient(i, v.get(i, 0));

        return e;
    }

    public static double approximate(Equation eq, double x, double y){
        var V = new Vector(equationLength);

        int i, j;
        for(j = 0; j < equationCount; ++j){
            for(i = 0; i < pointCount; ++i){
                V.setComponent(i + 4 * j, Math.pow(x, i) * Math.pow(y, j));
            }
        }

        return eq.apply(V);
    }
}
