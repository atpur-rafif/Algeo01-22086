import Image.ImageLoader;
import Image.ImageSaver;
import Image.Resize;
import Matrix.*;
import Matrix.OBE.OBERunner;
import Vector.EquationSpace;
import Vector.EuclideanSpace;
import Vector.VectorSpace;
import Menu.*;
import Application.*;

import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        var eq = new EquationSpace(16);
        for(int i = 0; i < 16; ++i) eq.set(i, i + 1); 

        System.out.println(StringFormatter.polynomialEquation(eq));
    }
}