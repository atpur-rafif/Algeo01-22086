import Matrix.*;
import Application.*;

public class Test {
    public static void main(String[] args){
        var p = BicubicSpline.createMatrix();
        MatrixPrinter.print(p);
    }
}