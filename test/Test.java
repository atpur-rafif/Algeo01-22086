import Matrix.*;

import Application.*;
import Matrix.*;
import Point.*;

public class Test {
    public static void main(String[] args){
        var m = MatrixReader.read();
        var eq = BicubicSpline.getEquation(Vector.fromMatrix(m));
        System.out.println(BicubicSpline.approximate(eq, 1, 1));
        /*
        var image = ImageLoader.load("./tmp/debug.png");
        var resized = Resize.resize(image, 1.5);
        ImageSaver.save(resized, "./tmp/debug-1.5x.png");
        */
    }
}