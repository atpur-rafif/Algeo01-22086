import Matrix.*;

import Application.*;
import Matrix.*;
import Point.*;
import Transformation.BicubicSplineTranformation;
import Image.*;

public class Test2 {
    public static void main(String[] args){
        var M = MatrixReader.read();
        System.out.println(MatrixDeterminantWithOBE.determinantOBE(M));
    }
}