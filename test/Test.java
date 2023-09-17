import Matrix.*;
import Application.*;

public class Test {
    public static void main(String[] args){
        var M = BicubicSpline.createMatrix();
        MatrixPrinter.print(M);
    }
}