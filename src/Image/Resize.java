package Image;

import Application.BicubicSpline;
import Matrix.Matrix;
import Matrix.MatrixArithmetic;
import Point.Equation;

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

    public static Grayscale resize(Grayscale image, double size){
        var newHeight = (int) (image.height * size);
        var newWidth = (int) (image.width * size);
        var resized = new Grayscale(newHeight, newWidth);

        var cache = new Equation[image.height][image.width];
        var cacheStatus = new boolean[image.height][image.width];
        for(int i = 0; i < image.height; ++i){
            for(int j = 0; j < image.width; ++j){
                cacheStatus[i][j] = false;
            }
        }

        int x, y;
        for(y = 0; y < newHeight; ++y){
            for(x = 0; x < newWidth; ++x){
                // TODO: use transformation matrix?
                double mapX = (x / size);
                double mapY = (y / size);

                int mapIntX = (int) mapX;
                int mapIntY = (int) mapY;

                double mapFracX = mapX - mapIntX;
                double mapFracY = mapY - mapIntY;

                Equation A;

                if(!cacheStatus[mapIntY][mapIntX]){
                    var I = getVarMatrixFromPivot(mapIntX, mapIntY, image);
                    var DI = MatrixArithmetic.Multiply(ResizingMatrix.MatrixD, I);
                    A = BicubicSpline.getEquation(DI);
                    cache[mapIntY][mapIntX] = A;
                } else {
                    A = cache[mapIntY][mapIntX];
                }

                var p = (int) BicubicSpline.approximate(A, mapFracX, mapFracY);
                resized.setPixelCartesian(x, y, p);
            }
        }

        return resized;
    }
}
