import Matrix.*;

import Application.*;
import Matrix.*;
import Point.*;
import Transformation.BicubicSplineTranformation;
import Image.*;

public class Test2 {
    public static void main(String[] args){
        var M = MatrixReader.read();
        //var B = MatrixReader.read();

        //var solution = MatrixCramer.cramer(M,B);
        //for (int i=0;i<M.col;i++){
        //    System.out.println(solution[i]);
        //}

        System.out.println(MatrixDeterminantWithOBE.determinantOBE(M));
    }
}