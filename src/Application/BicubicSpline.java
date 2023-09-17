package Application;

import Matrix.*;
import Point.EquationBySampling;

public class BicubicSpline {
    private static int maxDegree = EquationBySampling.maxDegree;
    private static int equationLength = EquationBySampling.equationLength;
    private static int pointCount = EquationBySampling.pointCount;
    private static int equationCount = EquationBySampling.equationCount;

    private static double pow(double x, int y){
        double r = 1;
        for(; y > 0; --y) r *= x;
        return r;
    }

    public static Matrix MatrixX = EquationBySampling.createMatrix((var p) -> {
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
    public static Matrix getEquation(Matrix F){
        return MatrixArithmetic.Multiply(MatrixIX, F);
    }

    public static double approximate(Matrix equation, double x, double y){
        var M = new Matrix(1, equationLength);

        int i, j;
        for(j = 0; j < equationCount; ++j){
            for(i = 0; i < pointCount; ++i){
                M.set(0, i + 4 * j, Math.pow(x, i) * Math.pow(y, j));
            }
        }

        var R = MatrixArithmetic.Multiply(M, equation);

        return R.get(0, 0);
    }
}
