package Application;

import Matrix.*;

public class BicubicSpline {
    static int maxDegree = 3;
    static int equationLength = (int) pow(maxDegree + 1, 2);

    static double pow(double x, int y){
        double r = 1;
        for(; y > 0; --y) r *= x;
        return r;
    }

    static Point[] points = {
        new Point(0, 0),
        new Point(0, 1),
        new Point(1, 0),
        new Point(1, 1)
    };

    static double[][] createEquation(double x, double y){
        double[] f = new double[equationLength];
        double[] f_x = new double[equationLength];
        double[] f_y = new double[equationLength];
        double[] f_xy = new double[equationLength];

        int i, j, k = 0;
        for(i = 0; i <= maxDegree; ++i){
            for(j = 0; j <= maxDegree; ++j){
                f[k] = pow(x, i) * pow(x, j);
                f_x[k] = i * pow(x, i - 1) * pow(x, j);
                f_y[k] = j * pow(x, i) * pow(y, j - 1);
                f_xy[k] = i * j * pow(x, i - 1) * pow(y, j - 1);
                ++k;
            }
        }
        
        double[][] r = {f, f_x, f_y, f_xy};
        return r;
    }

    public static Matrix createMatrix(){
        var M = new Matrix(equationLength, equationLength);
        var Manipulator = new MatrixManipulator(M);

        int i, j;
        for(i = 0; i <= maxDegree; ++i){
            var p = points[i];
            var e = createEquation(p.x, p.y);
            for(j = 0; j <= maxDegree; ++j){
                Manipulator.setRow(j * 4 + i, e[j]);
            }
        }

        M = Manipulator.getResult();

        return M;
    }
}
