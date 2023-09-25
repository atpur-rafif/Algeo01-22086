import Matrix.*;
import Matrix.OBE.OBERunner;
import Application.*;
import Matrix.*;
import Point.*;
import Transformation.BicubicSplineTranformation;
import Image.*;

public class Test2 {
    public static void main(String[] args){
        var M = MatrixReader.read();
        var O = new OBERunner(M);
        O.gaussJordanElimination_v2();
        MatrixPrinter.print(O.getResult());
    }
}