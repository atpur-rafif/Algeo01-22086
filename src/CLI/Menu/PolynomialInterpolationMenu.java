package CLI.Menu;

import java.util.Scanner;
import Matrix.Matrix;
import Vector.EquationSpace;
import Application.*;
import CLI.IO.IOFile;
import CLI.IO.IONavigator;
import CLI.IO.IOPrompter;
import CLI.IO.IOStringFormatter;

public class PolynomialInterpolationMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void Run(){
        IONavigator.next("Polynomial Interpolation");
        while(true){
            IOPrompter.printMultiLine(new String[]{
                "1. CLI",
                "2. File",
                "3. Back"
            });
            var choice = IOPrompter.getBoundedInt("Input Type> ", 1, 3);

            if(choice == 3) break;

            double x = 0.0;
            Matrix matrix = null;

            IONavigator.next("Input");
            if(choice == 1){
                var row = IOPrompter.getInteger("Masukkan banyaknya sampel: ");
                var col = 2;
                System.out.println("Masukkan sampel: ");
                matrix = IOPrompter.getMatrix(row, col);
                x = IOPrompter.getDouble("Masukkan x untuk interpolasi: ");
            } else if(choice == 2){
                var t = IOFile.readObscureFormat();
                matrix = t.matrix;
                x = t.vector.get(0);
            };
            IONavigator.back();

            IONavigator.next("Result");
            EquationSpace eq = PolynomialInterpolation.solve(matrix);
            double result = PolynomialInterpolation.approximate(eq, x);

            var s = IOStringFormatter.polynomialEquation(eq) + "\n" + "f("+x+") = "+ result;
            System.out.println(s);
            IOFile.askToSave(s);
            IONavigator.back();

            IONavigator.reload();
        }
        IONavigator.back();
    }
}
