package Application;

import Matrix.*;
import Point.EquationBySampling;

public class BicubicSpline {
    private static int maxDegree = EquationBySampling.maxDegree;
    private static int equationLength = EquationBySampling.equationLength;

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
}
