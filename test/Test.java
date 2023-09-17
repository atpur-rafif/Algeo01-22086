import Matrix.*;

import Application.*;
import Point.Point;
import Image.EquationSolver;
import Image.ImageLoader;
import Image.ImageSaver;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Test {
    public static void main(String[] args){
        var r = EquationSolver.MatrixD;
        MatrixPrinter.print(r);
    }
}