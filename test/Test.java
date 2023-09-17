import Matrix.*;

import Application.*;
import Matrix.*;
import Image.ImageLoader;
import Image.ImageSaver;
import Image.ResizingMatrix;

public class Test {
    public static void main(String[] args){
        Matrix sampleF = MatrixReader.read();
        Matrix equation = BicubicSpline.getEquation(sampleF);

        System.out.println();
        MatrixPrinter.print(equation);
        System.out.println();
        var r = BicubicSpline.approximate(equation, 0, 1);
        System.out.println();
        System.out.print(r);
    }
}