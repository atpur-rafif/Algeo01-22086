package Image;

import Matrix.*;
import Point.EquationBySampling;

public class ResizingMatrix {
    private static int equationLength = EquationBySampling.equationLength;
    private static int sampleSideCount = 4;
    private static int coordinateOffset = (-1) * sampleSideCount + (-1);
    private static int flattenCoordinate(int x, int y){
        return (y * sampleSideCount) + x - coordinateOffset;
    }

    public static Matrix MatrixD = EquationBySampling.createMatrix((var p) -> {
        int x = (int) p.x, y = (int) p.y;

        double[] f = new double[equationLength];
        f[flattenCoordinate(x, y)] = 1;

        double[] f_x = new double[equationLength];
        f_x[flattenCoordinate(x + 1, y)] = 0.5;
        f_x[flattenCoordinate(x - 1, y)] = -0.5;

        double[] f_y = new double[equationLength];
        f_y[flattenCoordinate(x, y + 1)] = 0.5;
        f_y[flattenCoordinate(x, y - 1)] = -0.5;

        double[] f_xy = new double[equationLength];
        f_xy[flattenCoordinate(x + 1, y + 1)] = 0.25;
        f_xy[flattenCoordinate(x - 1, y    )] = -0.25;
        f_xy[flattenCoordinate(x    , y - 1)] = -0.25;
        f_xy[flattenCoordinate(x    , y    )] = -0.25;

        double[][] r = {f, f_x, f_y, f_xy};
        return r;
    });
}
