package Image;

import Application.*;
import Vector.*;

public class Resize {
    private static int equationLength = ResizingMatrix.equationLength;

    private static EuclideanSpace getVarMatrixFromPivot(int x, int y, Grayscale image){
        var V = new EuclideanSpace(equationLength);

        int ly, lx;
        for(ly = -1; ly <= 2; ++ly){
            for(lx = -1; lx <= 2; ++lx){
                var p = ResizingMatrix.flattenCoordinate(lx, ly);
                V.set(p, image.getPixelCartesian(x + lx, y + ly));
            }
        }

        return V;
    }

    public static Grayscale resize(Grayscale image, double size){
        var newHeight = (int) (image.height * size);
        var newWidth = (int) (image.width * size);
        var resized = new Grayscale(newWidth, newHeight);

        var cache = new EquationSpace[image.height][image.width];
        var cacheStatus = new boolean[image.height][image.width];
        for(int i = 0; i < image.height; ++i){
            for(int j = 0; j < image.width; ++j){
                cacheStatus[i][j] = false;
            }
        }

        int x, y;
        for(y = 0; y < newHeight; ++y){
            for(x = 0; x < newWidth; ++x){
                double mapX = (x / size);
                double mapY = (y / size);

                int mapIntX = (int) mapX;
                int mapIntY = (int) mapY;

                double mapFracX = mapX - mapIntX;
                double mapFracY = mapY - mapIntY;

                EquationSpace A;

                if(!cacheStatus[mapIntY][mapIntX]){
                    var I = getVarMatrixFromPivot(mapIntX, mapIntY, image);
                    var DI = ResizingMatrix.transformation.apply(I);
                    A = BicubicSpline.getEquation(DI);
                    cache[mapIntY][mapIntX] = A;
                } else {
                    A = cache[mapIntY][mapIntX];
                }

                var p = (int) BicubicSpline.approximate(A, mapFracX, mapFracY);
                resized.setPixelCartesian(x, y, p);
            }
            System.out.printf("%.2f", (100 * y) / (double) newHeight);
            System.out.print("%\n");
        }

        return resized;
    }
}
