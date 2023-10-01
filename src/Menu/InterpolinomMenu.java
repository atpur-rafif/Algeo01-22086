package Menu;

import java.util.Scanner;
import Matrix.Matrix;
import Matrix.MatrixReader;
import Application.*;

public class InterpolinomMenu {
    static Scanner scanner = new Scanner(System.in);

    public static void Run(){
        while(true){
            var choice = Prompter.getBoundedInt(new String[]{
                "====Polimonial Interpolation====",
                "1. CLI",
                "2. File",
                "3. Back"
            }, 1, 3);

            if(choice == 3) break;

            Matrix matrix = null;
            if(choice == 1) matrix = MatrixReader.readCLI();
            else if(choice == 2);

            var x = Prompter.getDoubleInline("LMAO");
            double result = PolynomialInterp.f(matrix, x);
            System.out.println("Hasil: " + result);

            IOFile.askToSave(Double.toString(result));
        }
    }
}
