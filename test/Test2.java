import Matrix.*;
import Matrix.OBE.OBERunner;
import Menu.MainMenu;
import Application.*;
import CLI.IO.IOStringFormatter;
import Matrix.*;
import Point.*;
import Transformation.BicubicSplineTranformation;
import Image.*;

public class Test2 {
    public static void main(String[] args){
        var m = MatrixReader.readCLI();
        var n = MatrixReader.readCLI();
        System.out.println(IOStringFormatter.matrix(MatrixArithmetic.Multiply(m,n)));
    }
}