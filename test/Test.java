import Matrix.*;
import Application.*;

public class Test {
    public static void main(String[] args){
        var M = BicubicSpline.createMatrix();
        var R = MatrixInverse.calculateWithGaussJordan(M);
        MatrixPrinter.print(M);
        System.out.println("\n");
        MatrixPrinter.print(R);
        
        System.out.println("\n");
        MatrixPrinter.print(MatrixArithmetic.Multiply(M, R));


        
        System.out.println("");
    }
}