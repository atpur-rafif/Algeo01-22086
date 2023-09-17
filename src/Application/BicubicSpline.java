package Application;

import Matrix.*;
import Point.Point;

public class BicubicSpline {
    private static int maxDegree = 3;
    private static int equationLength = 16;

    private static double pow(double x, int y){
        double r = 1;
        for(; y > 0; --y) r *= x;
        return r;
    }

    private static Point[] points = {
        new Point(0, 0),
        new Point(1, 0),
        new Point(0, 1),
        new Point(1, 1)
    };

    private static double[][] createEquation(Point p){
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
    }

    private static Matrix createMatrix(){
        var M = new Matrix(equationLength, equationLength);
        var Manipulator = new MatrixManipulator(M);

        int i, j;
        for(i = 0; i <= maxDegree; ++i){
            var e = createEquation(points[i]);
            for(j = 0; j <= maxDegree; ++j){
                Manipulator.setRow(j * 4 + i, e[j]);
            }
        }

        M = Manipulator.getResult();

        return M;
    }

    public static Matrix MatrixX = createMatrix();
}
