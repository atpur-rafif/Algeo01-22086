package CLI.Menu;

import java.util.Scanner;
import Matrix.Matrix;
import Application.*;
import CLI.IO.IOFile;
import CLI.IO.IONavigator;
import CLI.IO.IOPrompter;
import CLI.IO.MatrixReader;

public class PolynomialInterpolationMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void Run(){
        while(true){
            IONavigator.next("Polynomial Interpolation");
            IOPrompter.printMultiLine(new String[]{
                "1. CLI",
                "2. File",
                "3. Back"
            });
            var choice = IOPrompter.getBoundedInt("Input Type> ", 1, 3);

            if(choice == 3) break;

            double x = 0.0;
            Matrix matrix = null;
            if(choice == 1){
                var row = IOPrompter.getInteger("Masukkan banyaknya sampel: ");
                var col = 2;
                System.out.println("Masukkan sampel: ");
                matrix = MatrixReader.readCLI(row, col);
                x = IOPrompter.getDouble("Tes: ");
            } else if(choice == 2);

            double result = PolynomialInterpolation.f(matrix, x);
            System.out.println("Hasil: " + result);

            IOFile.askToSave(Double.toString(result));
            IONavigator.back();
        }
    }
}
