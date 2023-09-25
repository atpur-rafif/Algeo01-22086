import Matrix.*;
import Matrix.OBE.OBERunner;
import Application.*;
import Matrix.*;
import Point.*;
import Transformation.BicubicSplineTranformation;
import Image.*;

public class Test2 {
    public static void main(String[] args){
        double[][] arr;
        arr = new double[3][2];
        arr[0][0] = 8;
        arr[0][1] = 2.0794;
        arr[1][0] = 9;
        arr[1][1] = 2.1972;
        arr[2][0] = 9.5;
        arr[2][1] = 2.2512;

        var solution = PolynomialInterp.solve(arr);
        
        for (int i=0;i<solution.length;i++){
            System.out.println(solution[i]);
        }

    }
}