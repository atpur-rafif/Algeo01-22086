import Matrix.*;

import Application.*;
import Matrix.*;
import Image.ImageLoader;
import Image.ImageSaver;
import Image.Resize;
import Image.ResizingMatrix;

public class Test {
    public static void main(String[] args){
        var p = new BicubicSpline();
        MatrixPrinter.print(p.matrix);
        /*
        var image = ImageLoader.load("./tmp/debug.png");
        var resized = Resize.resize(image, 1.5);
        ImageSaver.save(resized, "./tmp/debug-1.5x.png");
        */
    }
}