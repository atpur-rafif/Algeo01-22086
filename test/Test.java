import Matrix.*;

import Application.*;
import Image.EquationSolver;
import Image.ImageLoader;
import Image.ImageSaver;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Test {
    public static void main(String[] args){
        int i, j;
        for(i = -1; i <= 2; ++i){
            for(j = -1; j <= 2; ++j){
                System.out.println(j + "," + i + ": " + EquationSolver.flattenCoordinate(j, i));
            }
        }
    }
}