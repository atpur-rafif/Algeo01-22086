package Image;

import Application.BicubicSpline;
import Matrix.Matrix;
import Matrix.MatrixArithmetic;

public class Resize {
    private static int equationLength = ResizingMatrix.equationLength;

    private static Matrix getVarMatrixFromPivot(int x, int y, Grayscale image){
        var M = new Matrix(equationLength, 1);

        int ly, lx;
        for(ly = -1; ly <= 2; ++ly){
            for(lx = -1; lx <= 2; ++lx){
                var p = ResizingMatrix.flattenCoordinate(lx, ly);
                M.set(p, 0, image.getPixelCartesian(x + lx, y + ly));
            }
        }

        return M;
    }
    
    public static Grayscale resize(Grayscale image, int size){
        var resized = new Grayscale((int) Math.ceil(image.width * size), (int) Math.ceil(image.height * size));

        int x, y;
        for(y = 0; y < image.height; ++y){
            for(x = 0; x < image.width; ++x){
                var I = getVarMatrixFromPivot(x, y, image);
                var DI = MatrixArithmetic.Multiply(ResizingMatrix.MatrixD, I);
                var A = BicubicSpline.getEquation(DI);

                int px = size * x, py = size * y;

                int ly, lx;
                double div = size - 1;
                for(ly = 0; ly < size; ++ly){
                    for(lx = 0; lx < size; ++lx){
                        var p = (int) BicubicSpline.approximate(A, lx / div, ly / div);
                        resized.setPixelCartesian(px + lx, py + ly, p);
                    }
                }
            }
        }

        return resized;
    }
}
